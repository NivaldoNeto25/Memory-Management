package so;

import so.Pagina;

public class MemoriaVirtual {
    private Pagina[] espacoVirtual;

    public MemoriaVirtual(int tamanho){
        this.espacoVirtual = new Pagina[tamanho];
    }
    
}
