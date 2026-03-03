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

    public Client(String nome) {
        this.nome = nome;
    }

    public void connetti() {
        try {
            this.socket = new DatagramSocket();
            System.out.println("Client pronto per l'invio.");
        } catch (IOException e) {
            System.err.print("errore nella connessione");
        }
    }

    public void invia(String messaggio) {
        try {
            byte[] buffer = messaggio.getBytes();
            InetAddress address = InetAddress.getByName(nomeServer);

            // Crei il pacchetto con i dati, l'indirizzo e la porta
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);

            // Invii il pacchetto
            socket.send(packet);
            System.out.println("Messaggio inviato: " + messaggio);
        } catch (IOException e) {
            System.err.print("errore nell'invio");
        }
    }

    public void chiudi() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
            System.out.println("Socket chiuso.");
        }
    }
}
