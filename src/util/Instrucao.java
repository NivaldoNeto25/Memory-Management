package util;

public class Instrucao {

    // Classe POJO (Plain Old Java Object) cria um objeto focado apenas em acomodar dados

    private int enderecoVirtual;
    private char operacao;
    private Integer valor; // Tem q usar o Integer ao invés do int, para o caso de em uma operaçao de leitura (ex: 1-R) o "valor" nao receber o valor = 0. Se fosse int e fosse uma operaçao de leitura, o java forçaria o "valor" para 0.

    public Instrucao(int enderecoVirtual, char operacao, Integer valor) {
        this.enderecoVirtual = enderecoVirtual;
        this.operacao = operacao;
        this.valor = valor;
    }

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