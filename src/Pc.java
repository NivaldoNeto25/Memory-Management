import hardware.MMU;
import hardware.SystemClock;
import java.util.ArrayList;
import java.util.List;
import hardware.memoria.Disco;
import hardware.memoria.MemoriaFisica;
import so.MemoriaVirtual;
import util.FabricaDeEntradas;
import util.Instrucao;
import util.SeparadorInstrucao;

public class Pc {
    
    //de acordo o diagrama é x
    public static final int TAMANHO_MEMORIA_FISICA = 16; //pode ser alterada e n vai ter que mexer em mais nada p fazer essa alteração
    
    // de acordo o diagrama q a gente montou onde era 2x
    public static final int TAMANHO_DISCO = TAMANHO_MEMORIA_FISICA * 2; //pode ser alterada e n vai ter que mexer em mais nada p fazer essa alteração
    
    // de acordo o diagrama q a gente montou onde era 2x
    public static final int TAMANHO_MEMORIA_VIRTUAL = TAMANHO_MEMORIA_FISICA * 2; //pode ser alterada e n vai ter que mexer em mais nada p fazer essa alteração

    public static final int VELOCIDADE_CLOCK = 10;

    public static final int NUM_THREADS = 2; // número de threads

    private MemoriaFisica memoriaFisica;
    private MemoriaVirtual memoriaVirtual;
    private Disco discoRigido;
    private SystemClock systemClock;
    private MMU mmu;

    public Pc(){
        //para iniciar as memorias com os seus tamanhos
        this.memoriaFisica = new MemoriaFisica(TAMANHO_MEMORIA_FISICA);
        this.memoriaVirtual = new MemoriaVirtual(TAMANHO_MEMORIA_VIRTUAL);
        this.discoRigido = new Disco(TAMANHO_DISCO);
        this.systemClock = new SystemClock(VELOCIDADE_CLOCK);
        this.mmu = new MMU(memoriaFisica, memoriaVirtual, discoRigido, systemClock);
    }


    // Liga a máquina
    public void boot(){
        System.out.println("Ligando");
        // cria a fábrica e já extrai a string
        String sequenciaComandos = new FabricaDeEntradas(TAMANHO_MEMORIA_VIRTUAL).getNewEntrada(); 
        
        // passa a string para o separador
        SeparadorInstrucao separador = new SeparadorInstrucao();
        List<Instrucao> listaInstrucoes = separador.separar(sequenciaComandos);
        
        System.out.println("--- Operações ---");
        System.out.println(listaInstrucoes);

        int totalInstrucoes = listaInstrucoes.size();
        int instrucoesPorThread = totalInstrucoes / NUM_THREADS;
        int resto = totalInstrucoes % NUM_THREADS;
        int indiceInicio = 0;
        
        // laço for para distribuir as instruçoes para as threads, se baseando no numero de threads
        for (int i = 0; i < NUM_THREADS; i++) {
            // Se houver resto na divisão entrega instrução a mais para a thread atual
            int qtdParaThreadAtual = instrucoesPorThread + (resto > 0 ? 1 : 0);
            resto--; 
            
            int indiceFim = indiceInicio + qtdParaThreadAtual;
            
            // vai cortar a lista em pedaços e passar cada pedaço para uma thread
            List<Instrucao> instrucoesSeparadas = new ArrayList<>(listaInstrucoes.subList(indiceInicio, indiceFim));
            
            // Cria a Thread passando um ID e a quantidade de instruções dela
            //ProcessoThread thread = new ProcessoThread(i + 1, instrucoesSeparadas, mmu);
            //thread.start();
            
            // Prepara o índice inicial para a próxima thread
            indiceInicio = indiceFim;
        }

        //systemClock.start();
    }

    // Desliga a máquina
    public void turnOff(){
        //systemClock.pararClock();
        System.out.println("Desligando");
    }

    //aq tem os getters para mmu, so e o que mais precisar ter acesso a essas memorias
    public MemoriaFisica getMemoriaFisica(){
        return memoriaFisica;
    }

    public MemoriaVirtual getMemoriaVirtual(){
        return memoriaVirtual;
    }

    public Disco getDiscoRigido(){
        return discoRigido;
    }

    //getters para o clock do sistema
    public SystemClock getSystemClock(){
        return systemClock;
    }
}
