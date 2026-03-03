package server;

import java.util.Objects;

public class ServerMain {
    public static void main(String[] args) {
        int port = 5000;
        Server server = new Server(port);
        System.out.println("Avvio del server UDP...");
        while(!server.getMessaggio().equals("stop")) {
            server.attendi();
            server.leggi();
            server.scrivi();
        }
        System.out.println("Operazione completata.");
    }
}
