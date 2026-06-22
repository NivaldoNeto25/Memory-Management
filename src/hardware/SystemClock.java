package hardware;

public class SystemClock extends Thread {
    
    // Variáveis volatile para ser Thread-safe quando são alteradas as thread vão saber
    private volatile int tempoAtual;
    private volatile boolean rodando;
    
    // Define quanto tempo real (em milissegundos) equivale a 1 "tick" do sistema
    private final int tempoTickMs;

    public SystemClock(int tempoTickMs) {
        this.tempoAtual = 0;
        this.rodando = true;
        this.tempoTickMs = tempoTickMs;
    }

    public SystemClock() {
        // Construtor padrão com 10ms por tick
        this(10);
    }

    @Override
    public void run() {
        while (rodando) {
            try {
                // A thread dorme por 'tempoTickMs' simulando um ciclo de clock
                Thread.sleep(tempoTickMs);
                tempoAtual++;
            } catch (InterruptedException e) {
                System.out.println("Relógio do sistema interrompido.");
                Thread.currentThread().interrupt(); 
            }
        }
    }

    // Método que será usado pelo Algoritmo WS para saber a "hora" atual
    public int getTempoAtual() {
        return tempoAtual;
    }

    // Método para encerrar a thread
    public void pararClock() {
        this.rodando = false;
    }
}