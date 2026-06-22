import java.util.List;

import hardware.memoria.Disco;
import hardware.memoria.MemoriaFisica;
import so.MemoriaVirtual;
import util.FabricaDeEntradas;
import util.Instrucao;
import util.LeitorInstrucao;

public class Pc {
    
    //de acordo o diagrama é x
    public static final int TAMANHO_MEMORIA_FISICA = 16; //pode ser alterada e n vai ter que mexer em mais nada p fazer essa alteração
    
    // de acordo o diagrama q a gente montou onde era 2x
    public static final int TAMANHO_DISCO = TAMANHO_MEMORIA_FISICA * 2; //pode ser alterada e n vai ter que mexer em mais nada p fazer essa alteração
    
    // de acordo o diagrama q a gente montou onde era 2x
    public static final int TAMANHO_MEMORIA_VIRTUAL = TAMANHO_MEMORIA_FISICA * 2; //pode ser alterada e n vai ter que mexer em mais nada p fazer essa alteração
    
    private MemoriaFisica memoriaFisica;
    private MemoriaVirtual memoriaVirtual;
    private Disco discoRigido;

    public Pc(){
        //para iniciar as memorias com os seus tamanhos
        this.memoriaFisica = new MemoriaFisica(TAMANHO_MEMORIA_FISICA);
        this.memoriaVirtual = new MemoriaVirtual(TAMANHO_MEMORIA_VIRTUAL);
        this.discoRigido = new Disco(TAMANHO_DISCO);
    }

    public void iniciar() {        
        // cria a fábrica e já extrai a string
        String sequenciaComandos = new FabricaDeEntradas(TAMANHO_MEMORIA_VIRTUAL).getNewEntrada(); 
        
        // passa a string para o leitor
        LeitorInstrucao leitor = new LeitorInstrucao();
        List<Instrucao> listaInstrucoes = leitor.separar(sequenciaComandos);
        
        System.out.println("--- Operações ---");
        System.out.println(listaInstrucoes);
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
}
