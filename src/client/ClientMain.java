package client;

public class ClientMain {
    public static void main(String[] args) {
        Client client = new Client("gab");
        client.connetti();
        client.invia("Ciao dal client di " + client);

        client.chiudi();
    }
}
