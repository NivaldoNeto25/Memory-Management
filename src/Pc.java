import hardware.SystemClock;
import hardware.memoria.Disco;
import hardware.memoria.MemoriaFisica;
import so.MemoriaVirtual;

public class Pc {
    
    //de acordo o diagrama é x
    public static final int TAMANHO_MEMORIA_FISICA = 16; //pode ser alterada e n vai ter que mexer em mais nada p fazer essa alteração
    
    // de acordo o diagrama q a gente montou onde era 2x
    public static final int TAMANHO_DISCO = TAMANHO_MEMORIA_FISICA * 2; //pode ser alterada e n vai ter que mexer em mais nada p fazer essa alteração
    
    // de acordo o diagrama q a gente montou onde era 2x
    public static final int TAMANHO_MEMORIA_VIRTUAL = TAMANHO_MEMORIA_FISICA * 2; //pode ser alterada e n vai ter que mexer em mais nada p fazer essa alteração

    public static final int VELOCIDADE_CLOCK = 10;

    private MemoriaFisica memoriaFisica;
    private MemoriaVirtual memoriaVirtual;
    private Disco discoRigido;
    private SystemClock systemClock;

    public Pc(){
        //para iniciar as memorias com os seus tamanhos
        this.memoriaFisica = new MemoriaFisica(TAMANHO_MEMORIA_FISICA);
        this.memoriaVirtual = new MemoriaVirtual(TAMANHO_MEMORIA_VIRTUAL);
        this.discoRigido = new Disco(TAMANHO_DISCO);
        this.systemClock = new SystemClock(VELOCIDADE_CLOCK);
    }


    // Liga a máquina
    public void boot(){
        systemClock.start();
        System.out.println("Ligando");
    }

    // Desliga a máquina
    public void turnOff(){
        systemClock.pararClock();
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
