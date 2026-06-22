package so;

import so.Pagina;

public class MemoriaVirtual {
    private Pagina[] espacoVirtual;

    public MemoriaVirtual(int tamanho){
        this.espacoVirtual = new Pagina[tamanho];
    }
    
    public void registrarPagina(int enderecoVirtual, Pagina pagina){ //vai ser usado na inicializacao do sistema p criar as paginas logicas
        this.espacoVirtual[enderecoVirtual] = pagina;
    }

    public Pagina getPaginaLogica(int enderecoVirtual){ //vai retornar a pagina baseado no endereco logico solicitado pela instrucao
        return this.espacoVirtual[enderecoVirtual];
    }

    public int getTamanhoTotal(){ //aq e para a mmu saber o limite dos enderecos
        return this.espacoVirtual.length;
    }
}
