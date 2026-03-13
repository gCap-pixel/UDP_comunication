package server;

/**
 * Classe principale per avviare il Server.
 * Resta in ascolto sulla porta e stampa i messaggi ricevuti con l'IP del mittente.
 */
public class ServerMain {
    /**
     * Gestisce il ciclo di ricezione e chiude il server se riceve "stop".
     */
    public static void main(String[] args) {
        int port = 5000;
        Server server = new Server(port);

        // Stringa per controllare il messaggio ricevuto
        String ricevuto = "";

        // Continua a ricevere finché il messaggio non è "stop"
        while(!ricevuto.equalsIgnoreCase("stop")) {
            server.attendi(); // Prepara il buffer di ricezione
            server.leggi();   // Aspetta il pacchetto (bloccante)

            // Prende il messaggio ricevuto e toglie gli spazi inutili
            ricevuto = server.getMessaggio().trim();

            System.out.println("Server ha letto da " + server.getIp()+": "+ ricevuto);
        }

        System.out.println("Server spento.");
    }
}
