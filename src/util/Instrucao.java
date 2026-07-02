package util;

public class Instrucao {

    // Classe cria um objeto focado apenas em acomodar dados.
    // Não possui lógica de negócio, cálculos ou comportamento complexo.

    private int enderecoVirtual;
    private char operacao;
    
    private Integer valor;      // Tem q usar o Integer ao invés do int, para o caso de em uma operaçao de leitura (ex: 1-R) 
                                // o "valor" nao recebe valor = 0. Se fosse int, o Java forçaria o "valor" para 0, 
                                // Isso para o caso de operações de leitura

    // Constutor para operações de escrita ('W')
    public Instrucao(int enderecoVirtual, char operacao, Integer valor) {
        this.enderecoVirtual = enderecoVirtual;
        this.operacao = operacao;
        this.valor = valor;
    }

    // Construtor para operações de leitura ('R')
    public Instrucao(int enderecoVirtual, char operacao) {
        this.enderecoVirtual = enderecoVirtual;
        this.operacao = operacao;
    }

    @Override
    public String toString() {
        // Se for operação de escrita, mostramos o valor também
        if (this.operacao == 'W') {
            return "[" + this.enderecoVirtual + "-" + this.operacao + "-" + this.valor + "]";
        } else {
            // Se for leitura, mostramos só endereço e operação
            return "[" + this.enderecoVirtual + "-" + this.operacao + "]";
        }
    }

    // Não possui Setters para não modificar os dados
    public int getEnderecoVirtual() { 
        return enderecoVirtual; 
    }
    
    public char getOperacao() { 
        return operacao; 
    }
    
    public Integer getValor() { 
        return valor; 
    }
}