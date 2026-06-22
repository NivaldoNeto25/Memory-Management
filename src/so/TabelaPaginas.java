package so;


public class TabelaPaginas {
    
    // O array que representa a tabela da imagem. O índice é a página virtual.
    private Pagina[] paginas;

    public TabelaPaginas(int tamanhoMemoriaVirtual) {
        this.paginas = new Pagina[tamanhoMemoriaVirtual];
        
        // Inicializa a tabela, no começo todas as páginas estão ausentes na memória física.
        for (int i = 0; i < tamanhoMemoriaVirtual; i++) {
            this.paginas[i] = new Pagina();
        }
    }

    // para MMU saber o endereço
    public Pagina getPagina(int indicePaginaVirtual) {
        return this.paginas[indicePaginaVirtual];
    }

    // para o WS iterar nas páginas
    public Pagina[] getTodasPaginas() {
        return this.paginas;
    }
    
    public int getTamanhoTotal() {
        return this.paginas.length;
    }
}
