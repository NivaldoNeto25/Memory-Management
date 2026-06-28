package hardware;

import hardware.memoria.Disco;
import hardware.memoria.MemoriaFisica;
import so.MemoriaVirtual;
import so.Pagina;
import util.Instrucao;
import util.Printer;

public class MMU {
    
    private MemoriaFisica memoriaRam;
    private Disco discoRigido;
    private MemoriaVirtual vm;
    private SystemClock clock;

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
            atualizarStatusPagina(pagina, instrucao.getOperacao());
            
        } else {
            // PAGE FAULT
            // Como vai mexer no disco e na RAM, precisa trancar esse bloco
            synchronized (this) {
                
                // Checa se está presente, para o caso de outra thread ter trazido a pag enquanto a outra esperava fora do synchronized

                if (!pagina.isPresente()) {
                    Printer.log("Thread " + idThread, "Page Fault no endereco " + endLogico + ". Buscando no disco...");
                    
                    // Aqui vai chamar o algoritmo de substituição
                    
                    pagina.setPresente(true);
                }
            }
            
            // Depois que a página está na RAM, atualiza os bits
            atualizarStatusPagina(pagina, instrucao.getOperacao());
        }
    }

    // Método para atualizar os bits
    private void atualizarStatusPagina(Pagina pagina, char operacao) {
        pagina.setReferenciada(true);
        pagina.setTempoUltimoAcesso(clock.getTempoAtual());
        
        if (operacao == 'W') {
            pagina.setModificada(true);
        }
    }
}