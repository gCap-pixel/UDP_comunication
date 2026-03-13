package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Gestisce la spedizione e ricezione di messaggi UDP.
 */
public class Client {
    private String nome;
    private DatagramSocket socket;
    private String nomeServer = "localhost";
    private int port = 5000;

    /** Pacchetto per salvare l'ultimo messaggio arrivato */
    private DatagramPacket lastReceivedPacket;

    /**
     * Crea un client con un nome identificativo.
     * @param nome Nome del client.
     */
    public Client(String nome) {
        this.nome = nome;
    }

    /**
     * Crea la socket per la connessione.
     */
    public void creazione() {
        try {
            this.socket = new DatagramSocket();
            System.out.println("Client pronto.");
        } catch (IOException e) {
            System.err.println("Errore nella connessione");
        }
    }

    /**
     * Si mette in ascolto per leggere un pacchetto in arrivo.
     */
    public void leggi() {
        try {
            byte[] buffer = new byte[1024];
            lastReceivedPacket = new DatagramPacket(buffer, buffer.length);

            socket.receive(lastReceivedPacket);

            String messaggio = new String(lastReceivedPacket.getData(), 0, lastReceivedPacket.getLength());
            System.out.println("Ricevuto: " + messaggio);
        } catch (IOException e) {
            System.err.println("Errore nella lettura");
        }
    }

    /**
     * Risponde all'ultimo mittente che ha inviato un pacchetto.
     */
    public void scrivi() {
        try {
            if (lastReceivedPacket == null) return;

            String risposta = "Messaggio ricevuto correttamente";
            byte[] bufferRisposta = risposta.getBytes();

            InetAddress targetAddress = lastReceivedPacket.getAddress();
            int targetPort = lastReceivedPacket.getPort();

            DatagramPacket responsePacket = new DatagramPacket(bufferRisposta, bufferRisposta.length, targetAddress, targetPort);
            socket.send(responsePacket);
            System.out.println("Risposta inviata.");
        } catch (IOException e) {
            System.err.println("Messaggio non inviato");
        }
    }

    /**
     * Invia un messaggio al server predefinito.
     * @param messaggio Testo da spedire.
     */
    public void invia(String messaggio) {
        try {
            byte[] buffer = messaggio.getBytes();
            InetAddress address = InetAddress.getByName(nomeServer);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);
        } catch (IOException e) {
            System.err.println("Errore nell'invio");
        }
    }

    /**
     * Chiude la socket.
     */
    public void chiudi() {
        if (socket != null) socket.close();
    }

    /**
     * Restituisce il testo dell'ultimo pacchetto ricevuto.
     * @return Messaggio in formato stringa.
     */
    public String getMessaggio() {
        if (lastReceivedPacket != null) {
            return new String(lastReceivedPacket.getData(), 0, lastReceivedPacket.getLength());
        }
        return null;
    }
}
