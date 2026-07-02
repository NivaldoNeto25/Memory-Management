package so.swap;

import so.MemoriaVirtual;
import so.Pagina;
import util.Printer;

// O algoritmo Working Set (WS) monitora as páginas acessadas por um processo em uma janela de tempo recente (Δ). Ele mantém na memória RAM apenas essas páginas ativas e remove as que estão fora da janela.

public class AlgoritmoSub {
    
    private int tau; // tempo máximo aceitável para estar no conjunto de trabalho
    // se o tempo da pag for maior que o tau, essa pag vai ser a vítima do algoritmo

    public AlgoritmoSub(int tau) {
        this.tau = tau;
    }

    public Pagina executar(MemoriaVirtual vm, int tempoAtual, int idThread) {
        Pagina vitima = null;
        int maiorIdade = -1;
        Pagina paginaMaisVelha = null;

        Printer.log("Thread " + idThread, "Executando Algoritmo Working Set (tau = " + tau + ")...");

        for (Pagina p : vm.getTodasPaginas()) { 
            if (!p.isPresente()) continue; 

            if (p.isReferenciada()) { //indica que a pag foi acessada recentemente
                p.setReferenciada(false); 
            } else { // se não foi referenciada vai olhar as idades 
                int idade = tempoAtual - p.getTempoUltimoAcesso();
                if (idade > tau) { // escolhe a que for maior que a variável tau
                    vitima = p;
                    break; 
                }
                if (idade > maiorIdade) { // se não tiver pag fora do conjunto de trabalho, vai escolher a mais velha dentro do conjunto
                    maiorIdade = idade;
                    paginaMaisVelha = p;
                }
            }
        }

        if (vitima == null) {
            if (paginaMaisVelha != null) {
                vitima = paginaMaisVelha;
            } else {
                int menorTempo = Integer.MAX_VALUE;
                for (Pagina p : vm.getTodasPaginas()) { // escolhe com base no tempo do clock
                    if (p.isPresente() && p.getTempoUltimoAcesso() < menorTempo) {
                        menorTempo = p.getTempoUltimoAcesso();
                        vitima = p;
                    }
                }
            }
        }
        return vitima;
    }
}