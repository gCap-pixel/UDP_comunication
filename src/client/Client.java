package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    private String nome;
    private DatagramSocket socket;
    private String nomeServer = "localhost";
    private int port = 5000;

    // Serve un pacchetto d'appoggio per conservare l'ultimo messaggio ricevuto
    private DatagramPacket lastReceivedPacket;

    public Client(String nome) {
        this.nome = nome;
    }

    public void creazione() {
        try {
            this.socket = new DatagramSocket();
            System.out.println("Client pronto.");
        } catch (IOException e) {
            System.err.println("Errore nella connessione");
        }
    }

    public void leggi() {
        try {
            byte[] buffer = new byte[1024]; // Spazio per i dati in entrata
            lastReceivedPacket = new DatagramPacket(buffer, buffer.length);

            socket.receive(lastReceivedPacket); // Bloccante

            String messaggio = new String(lastReceivedPacket.getData(), 0, lastReceivedPacket.getLength());
            System.out.println("Ricevuto: " + messaggio);
        } catch (IOException e) {
            System.err.println("Errore nella lettura");
        }
    }

    public void scrivi() {
        try {
            if (lastReceivedPacket == null) return;

            String risposta = "Messaggio ricevuto correttamente";
            byte[] bufferRisposta = risposta.getBytes();

            // Recupero info dal mittente dell'ultimo pacchetto
            InetAddress targetAddress = lastReceivedPacket.getAddress();
            int targetPort = lastReceivedPacket.getPort();

            DatagramPacket responsePacket = new DatagramPacket(bufferRisposta, bufferRisposta.length, targetAddress, targetPort);
            socket.send(responsePacket);
            System.out.println("Risposta inviata.");
        } catch (IOException e) {
            System.err.println("Messaggio non inviato");
        }
    }

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

    public void chiudi() {
        if (socket != null) socket.close();
    }

    // Metodo per estrarre il testo dall'ultimo pacchetto ricevuto
    public String getMessaggio() {
        if (lastReceivedPacket != null) {
            return new String(lastReceivedPacket.getData(), 0, lastReceivedPacket.getLength());
        }
        return null;
    }
}
