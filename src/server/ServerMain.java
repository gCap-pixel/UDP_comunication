package server;

import java.util.Scanner;

/**
 * Classe principale per avviare il Server.
 * Resta in ascolto sulla porta e stampa i messaggi ricevuti con l'IP del mittente.
 */
public class ServerMain {
    /**
     * Gestisce il ciclo di ricezione e chiude il server se riceve "stop".
     */
    public static void main(String[] args) {
        int port = 12345;
        Server server = new Server(port);

        // Stringa per controllare il messaggio ricevuto
        String ricevuto = "";
        String messaggioInviato = "";
        Scanner tastiera = new Scanner(System.in);

        // Continua a ricevere finché il messaggio non è "stop"
        server.attendi();
        while(!ricevuto.equalsIgnoreCase("stop")) {

            server.leggi();
            // Prende il messaggio ricevuto e toglie gli spazi inutili
            ricevuto = server.getMessaggioSeparato();
            System.out.println("il client " + server.getNome()+" dice: "+ ricevuto);
            server.scrivi(ricevuto);


        }

        System.out.println("Server spento.");
    }
}
