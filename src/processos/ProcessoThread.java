package processos;

import hardware.MMU;
import util.Instrucao;
import util.Printer;
import java.util.List;

public class ProcessoThread extends Thread {
    
    private int idProcesso;
    private List<Instrucao> filaInstrucoes;
    private MMU mmu;

    public ProcessoThread(int idProcesso, List<Instrucao> filaInstrucoes, MMU mmu) {
        this.idProcesso = idProcesso;
        this.filaInstrucoes = filaInstrucoes;
        this.mmu = mmu;
    }

    @Override
    public void run() {
        Printer.log("Thread " + idProcesso, "Iniciada com " + filaInstrucoes.size() + " instruções.");
        
        // Varre cada instrução e manda para a MMU processar
        for (Instrucao inst : filaInstrucoes) {
            
            mmu.processarAcesso(inst, idProcesso);
            
            // Pausa de 100 milissegundos 
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Printer.log("Thread " + idProcesso, "Foi interrompida.");
            }
        }
        
        Printer.log("Thread " + idProcesso, "Finalizou todas as suas instrucoes com sucesso!");
    }
}