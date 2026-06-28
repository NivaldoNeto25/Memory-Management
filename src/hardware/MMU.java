package hardware;

import hardware.memoria.Disco;
import hardware.memoria.MemoriaFisica;
import so.MemoriaVirtual;
import so.Pagina;
import so.swap.AlgoritmoSub;
import util.Instrucao;
import util.Printer;

public class MMU {
    
    private MemoriaFisica memoriaRam;
    private Disco discoRigido;
    private MemoriaVirtual vm;
    private SystemClock clock;
    private AlgoritmoSub ws = new AlgoritmoSub(50);

    public MMU(MemoriaFisica memoriaRam, MemoriaVirtual vm, Disco discoRigido,  SystemClock clock) {
        this.memoriaRam = memoriaRam;
        this.discoRigido = discoRigido;
        this.vm = vm;
        this.clock = clock;
    }

    public void processarAcesso(Instrucao instrucao, int idThread) {
        
        int endLogico = instrucao.getEnderecoVirtual();
        String tipoOperacao = instrucao.getOperacao() == 'R' ? "Leitura" : "Escrita";
        
        Printer.log("Thread " + idThread, "Acessando endereco logico " + endLogico + " (" + tipoOperacao + ")");
        
        // As threads podem consultar a Tabela de Páginas simultaneamente
        Pagina pagina = vm.getPagina(endLogico);

        // Se a página já está na RAM não precisa trancar a MMU
        // O acesso acontece em paralelo
        if (pagina.isPresente()) {
            
            Printer.log("Thread " + idThread, "Pagina " + endLogico + " ja esta na RAM.");
            atualizarStatusPagina(pagina, instrucao);
            
        } else {
            // PAGE FAULT
            // Como vai mexer no disco e na RAM, precisa trancar esse bloco
            synchronized (this) {
                
                // Checa se está presente, para o caso de outra thread ter trazido a pag enquanto a outra esperava fora do synchronized

                if (!pagina.isPresente()) {
                    
                    if (!memoriaRam.isCheia()) {
                        
                        int quadroLivre = memoriaRam.encontrarQuadroLivre();
                        memoriaRam.alocarQuadro(quadroLivre); // aloca um quadro livre
                        
                        pagina.setPresente(true);
                        pagina.setMolduraPagina(quadroLivre); // Atualiza o Mapa
                        
                    } else {
                        // SWAP
                        Pagina vitima = ws.executar(vm, clock.getTempoAtual(), idThread); // Passa o Mapa
                        int quadroDaVitima = vitima.getMolduraPagina();
                        
                        // Swap out
                        // vai tirar o dado da ram e salvar em disco
                        // vai salvar o endereço do hd onde foi salvo
                        if (vitima.isModificada()) {
                            int blocoLivre = discoRigido.encontrarBlocoLivre();
                            if (blocoLivre != -1) {
                                Integer dadoFisico = memoriaRam.lerDado(quadroDaVitima);
                                discoRigido.salvarDado(blocoLivre, dadoFisico);
                                vitima.setBlocoDisco(blocoLivre);
                                Printer.log("Thread " + idThread, "Vitima modificada! Dado salvo no bloco " + blocoLivre + " do SWAP.");
                            }
                        }
                        
                        // tira a vítima
                        memoriaRam.liberarQuadro(quadroDaVitima);
                        vitima.setPresente(false);
                        vitima.setMolduraPagina(-1);
                        vitima.setModificada(false); 
                        vitima.setReferenciada(false);
                        
                        // A nova página entra na RAM
                        memoriaRam.alocarQuadro(quadroDaVitima);
                        pagina.setPresente(true);
                        pagina.setMolduraPagina(quadroDaVitima);
                        
                    }
                    
                    // Swap in
                    // se a página que entrou já tinha sofrido swap out, traz o dado de volta
                    // apaga o dado do hd e libera o bloco onde ele tava
                    if (pagina.getBlocoDisco() != -1) {
                        Integer dadoResgatado = discoRigido.recuperarDado(pagina.getBlocoDisco());
                        memoriaRam.escreverDado(pagina.getMolduraPagina(), dadoResgatado);
                        discoRigido.liberarBloco(pagina.getBlocoDisco());
                        pagina.setBlocoDisco(-1); 
                    }
                }
            }
        }
        atualizarStatusPagina(pagina, instrucao); // atualiza os bits
        
        Printer.log("Thread " + idThread, "RAM = " + memoriaRam.getEstadoMemoria());
        Printer.log("Thread " + idThread, "Disco = " + discoRigido.getEstadoDisco());
        System.out.println("--------------------------------------------------");
    }

    // Método para atualizar os bits
    private void atualizarStatusPagina(Pagina pagina, Instrucao instrucao) {
        pagina.setReferenciada(true);
        pagina.setTempoUltimoAcesso(clock.getTempoAtual());
        
        if (instrucao.getOperacao() == 'W') {
            pagina.setModificada(true);
            memoriaRam.escreverDado(pagina.getMolduraPagina(), instrucao.getValor());
        }
    }
}