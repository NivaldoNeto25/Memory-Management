package hardware.memoria;

import so.Pagina;

public class MemoriaFisica {

    private Pagina[] quadros; //representa os quadros (frames)

    public MemoriaFisica(int tamanho){
        this.quadros = new Pagina[tamanho];
    }

    public void alocarPagina(int indiceQuadro, Pagina pagina){
        this.quadros[indiceQuadro] = pagina; //traz pagina do disco ou cpu e bota na "ram"
    }

    public Pagina removerPagina(int indiceQuadro){ //remove a pagina da ram
        Pagina paginaRemovida = this.quadros[indiceQuadro];
        this.quadros[indiceQuadro] = null; //libera o espaco
        return paginaRemovida;
    }

    public Pagina getPagina(int indiceQuadro){ //vai retorna a pagina desse frame para mmu ler ou escrever
        return this.quadros[indiceQuadro];
    }

    public boolean isCheia(){ //vai ver se a memoria ta cheia. vai ser usado pela parte de substituicao
        for (Pagina p : quadros){
            if (p == null){
                return false;
            }
        }
        return true;
    }
}
