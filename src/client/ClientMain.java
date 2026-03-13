package client;

import java.util.Scanner;

/**
 * Classe principale per avviare il Client.
 * Permette all'utente di digitare messaggi da tastiera e inviarli via UDP.
 */
public class ClientMain {
    /**
     * Crea il client, apre la connessione e gestisce l'invio dei messaggi.
     */
    public static void main(String[] args) {
        Client client = new Client("gab");
        client.creazione();

        Scanner tastiera = new Scanner(System.in);
        String messaggioInviato = "";

        // Ciclo che continua finché non scrivi "stop"
        while(!"stop".equalsIgnoreCase(messaggioInviato)) {
            System.out.print("Scrivi messaggio: ");
            messaggioInviato = tastiera.nextLine(); // Aspetta l'input dell'utente

            client.invia(messaggioInviato); // Invia il messaggio al server
        }

        client.chiudi();
        tastiera.close();
    }
}
