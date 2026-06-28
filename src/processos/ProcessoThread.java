package processos;

import hardware.MMU;
import util.Instrucao;
import util.Printer;
import java.util.List;

// O 'extends Thread' permite que o Java rode essa classe em paralelo
public class ProcessoThread extends Thread {
    
    private int idProcesso;
    private List<Instrucao> filaInstrucoes;
    private MMU mmu;

    // Construtor recebe a identidade, a fatia de instruções exclusiva dela e o "cérebro" (MMU)
    public ProcessoThread(int idProcesso, List<Instrucao> filaInstrucoes, MMU mmu) {
        this.idProcesso = idProcesso;
        this.filaInstrucoes = filaInstrucoes;
        this.mmu = mmu;
    }

    // O método run() dita o que acontece no momento em que o PC dá o "thread.start()"
    @Override
    public void run() {
        Printer.log("Thread " + idProcesso, "Iniciada com " + filaInstrucoes.size() + " instruções.");
        
        // Varre cada instrução do seu lote e manda para a MMU processar
        for (Instrucao inst : filaInstrucoes) {
            
            mmu.processarAcesso(inst, idProcesso);
            
            // Pausa de 100 milissegundos para simular o tempo de processamento da CPU
            // Isso também serve para você conseguir ler o log no console com calma
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Printer.log("Thread " + idProcesso, "Foi interrompida.");
            }
        }
        
        Printer.log("Thread " + idProcesso, "Finalizou todas as suas instrucoes com sucesso!");
    }
}