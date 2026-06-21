public class Pc {
    
    public Pc(){
        //construtor vazio
    }
    //de acordo o diagrama é x
    public static final int TAMANHO_MEMORIA_FISICA = 16; //pode ser alterada e n vai ter que mexer em mais nada p fazer essa alteração
    
    // de acordo o diagrama q a gente montou onde era 2x
    public static final int TAMANHO_DISCO = TAMANHO_MEMORIA_FISICA * 2; //pode ser alterada e n vai ter que mexer em mais nada p fazer essa alteração
    
    // de acordo o diagrama q a gente montou onde era 2x
    public static final int TAMANHO_MEMORIA_VIRTUAL = TAMANHO_MEMORIA_FISICA * 2; //pode ser alterada e n vai ter que mexer em mais nada p fazer essa alteração
}
