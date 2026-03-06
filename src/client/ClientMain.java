package client;

public class ClientMain {
    private static String messaggioRicevuto = "i";
    public static void main(String[] args) {
        Client client = new Client("gab");
        client.connetti();
        while(!"stop".equals(messaggioRicevuto)) {
            client.scrivi();
            client.invia("Ciao dal client gab");
            client.leggi();
            messaggioRicevuto = client.getMessaggio();
        }
        client.chiudi();
    }
}
