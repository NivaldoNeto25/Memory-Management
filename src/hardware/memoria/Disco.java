package hardware.memoria;

public class Disco {

    private Integer[] blocos; //vai representar os blocos no disco (swap)
    private boolean[] ocupado; // mapa dos blocos livres/ocupados

    public Disco(int tamanho){
        this.blocos = new Integer[tamanho];
        this.ocupado = new boolean[tamanho];
    }
    
    // procura espaço no HD para salvar o dado que sofreu um swap
    public int encontrarBlocoLivre() {
        for (int i = 0; i < ocupado.length; i++) {
            if (!ocupado[i]) return i;
        }
        return -1;
    }

    // usado para salvar no HD
    public void salvarDado(int indiceBloco, Integer dado){
        this.ocupado[indiceBloco] = true;
        this.blocos[indiceBloco] = dado;
    }

    // usado para fazer swap in
    public Integer recuperarDado(int indiceBloco){
        return this.blocos[indiceBloco];
    }

    // apaga o dado do HD antes do swap in
    public void liberarBloco(int indiceBloco) {
        this.ocupado[indiceBloco] = false;
        this.blocos[indiceBloco] = null;
    }

    // exibe visualmente como está a memória
    public String getEstadoDisco() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < blocos.length; i++) {
            if (!ocupado[i] || blocos[i] == null) {
                sb.append("null");
            } else {
                sb.append(blocos[i]);
            }
            if (i < blocos.length - 1) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }
}
