package so;

public class MemoriaVirtual {
    
    private TabelaPaginas tabelaPaginas;

    public MemoriaVirtual(int tamanho){
        this.tabelaPaginas = new TabelaPaginas(tamanho);
    }

    public TabelaPaginas getTabelaPaginas() {
        return this.tabelaPaginas;
    }

    public Pagina getPagina(int enderecoVirtual) {
        return this.tabelaPaginas.getPagina(enderecoVirtual);
    }

    public int getTamanhoTotal() {
        return this.tabelaPaginas.getTamanhoTotal();
    }

    public Pagina[] getTodasPaginas() {
        return this.tabelaPaginas.getTodasPaginas();
    }

    public void carregarPaginaNaMemoriaFisica(int enderecoVirtual, int molduraFisica, int tempoAtual) {
        Pagina pag = this.getPagina(enderecoVirtual);
        pag.setPresente(true);
        pag.setMolduraPagina(molduraFisica);
        pag.setTempoUltimoAcesso(tempoAtual);
        pag.setModificada(false); 
    }

    public void removerPaginaDaMemoriaFisica(int enderecoVirtual) {
        Pagina pag = this.getPagina(enderecoVirtual);
        pag.setPresente(false);
        pag.setMolduraPagina(-1); 
    }
}
