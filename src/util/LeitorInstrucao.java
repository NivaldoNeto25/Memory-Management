package util;

public class LeitorInstrucao {

    // Recebe a string do gerador e devolve o objeto
    public Instrucao traduzir(String comandos) {
        
        // Corta a string quando encontrar hifens
        String[] partes = comandos.split("-");
        
        // Vai separar os dados nas variáveis
        int endereco = Integer.parseInt(partes[0]);
        char operacao = partes[1].toUpperCase().charAt(0); 
        
        // Escolhe o construtor baseado na operação
        if (operacao == 'W') {
            // Como é Escrita a posição [2] do array tem o valor/dado a ser escrito
            Integer valor = Integer.parseInt(partes[2]);
            return new Instrucao(endereco, operacao, valor);
            
        } else {
            // Sendo Leitura vai usar o construtor de 2 parâmetros.
            // O Java define o Integer valor como null 
            return new Instrucao(endereco, operacao);
        }
    }
}