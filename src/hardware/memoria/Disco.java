package hardware.memoria;

import so.Pagina;

public class Disco {

    private Pagina[] blocos; //vai representar os blocos no disco (swap)

    public Disco(int tamanho){
        this.blocos = new Pagina[tamanho];
    }
    
    public void salvarPagina(int indiceBloco, Pagina pagina){
        this.blocos[indiceBloco] = pagina; // da ram pro disco
    }

    public Pagina recuperarPagina(int indiceBloco){ //disco para a ram (libera espaço)
        Pagina paginaRecuperada = this.blocos[indiceBloco];
        this.blocos[indiceBloco] = null; //sai do disco
        return paginaRecuperada;
    }

    public Pagina consultarPagina(int indiceBloco){ //so vai ver oq tem 
        return this.blocos[indiceBloco];
    }
}
