package util;

import java.util.ArrayList;
import java.util.List;

public class SeparadorInstrucao {

    // Recebe a string do gerador e devolve uma lista de objetos
    public List<Instrucao> separar(String sequenciaComandos) {
        
        List<Instrucao> filaInstrucao = new ArrayList<>();

        // corta a string gigante nas vírgulas para separar as instruções
        // vira um array com ex: ["1-R", "2-W-50", "3-R"]
        String[] comando = sequenciaComandos.split(",");
        
        // laço for para separar todos os dados
        for (String instrucao : comando) {
            
            // agora vai separar baseado nos hifens
            String[] partes = instrucao.split("-");

            int endereco = Integer.parseInt(partes[0]);
            char operacao = partes[1].toUpperCase().charAt(0); 
            
            // Escolhe o construtor baseado na operação
            if (operacao == 'W') {
                // Como é Escrita, a posição [2] do array tem o valor/dado a ser escrito
                //2,W,50
                Integer valor = Integer.parseInt(partes[2]);
                
                // adicona a lista
                filaInstrucao.add(new Instrucao(endereco, operacao, valor));
                
            } else {
                // Sendo Leitura vai usar o construtor de 2 parâmetros.
                // adiciona a lista
                filaInstrucao.add(new Instrucao(endereco, operacao));
            }
        }
        
        // retorna a lista com os objetos
        return filaInstrucao;
    }
}