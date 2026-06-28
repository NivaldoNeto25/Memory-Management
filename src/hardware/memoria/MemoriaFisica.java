package hardware.memoria;

public class MemoriaFisica {

    private Integer[] quadros; //representa os quadros (frames), guarda os dados
    private boolean[] ocupado; // representa os quadros livres

    public MemoriaFisica(int tamanho){
        this.quadros = new Integer[tamanho];
        this.ocupado = new boolean[tamanho];
    }

    // Indica que um quadro foi ocupado
    public void alocarQuadro(int indiceQuadro){
        this.ocupado[indiceQuadro] = true; 
    }

    // apaga o dado da ram e marca o quadro como livre
    public void liberarQuadro(int indiceQuadro){
        this.ocupado[indiceQuadro] = false;
        this.quadros[indiceQuadro] = null; // Apaga o dado
    }

    // escreve o dado na RAM
    public void escreverDado(int indiceQuadro, Integer dado){
        this.quadros[indiceQuadro] = dado; 
    }

    // resgata valor salvo na ram
    public Integer lerDado(int indiceQuadro){
        return this.quadros[indiceQuadro];
    }

    // procura um quadro livre
    public int encontrarQuadroLivre() {
        for (int i = 0; i < ocupado.length; i++) {
            if (!ocupado[i]) return i;
        }
        return -1; // retorna -1 se a memoria estiver cheia
    }

    public boolean isCheia(){
        for (boolean status : ocupado){
            if (!status) return false;
        }
        return true;
    }

    // exibe visualmente como está a memória
    public String getEstadoMemoria() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < quadros.length; i++) {
            if (!ocupado[i] || quadros[i] == null) {
                sb.append("null");
            } else {
                sb.append(quadros[i]);
            }
            if (i < quadros.length - 1) sb.append(", ");
        }
        sb.append("}");
        return sb.toString();
    }
}
