package util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Printer {

    // padrão visual de tempo: Hora:Minuto:Segundo.Milissegundo
    private static final DateTimeFormatter formatadorTempo = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    //Se duas threads chamarem o print ao mesmo tempo, uma espera a outra terminar
    // O 'static' permite chamar Printer.log sem instanciar a classe
    public static synchronized void log(String emissor, String mensagem) {
        String horaAtual = LocalTime.now().format(formatadorTempo);
        
        // Vai mostrar no terminal a hora e a mensagem
        System.out.println("[" + horaAtual + "] [" + emissor + "]: " + mensagem);
    }
}