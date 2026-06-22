package hardware.memoria;

import so.Pagina;

public class Disco {

    private Pagina[] blocos;

    public Disco(int tamanho){
        this.blocos = new Pagina[tamanho];
    }
    
}
