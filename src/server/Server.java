package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Server {

    private int port;
    private DatagramSocket socket;
    private DatagramPacket lastReceivedPacket;
    String messaggio = "";
    public Server(int port) {
        this.port = port;
        try {
            this.socket = new DatagramSocket(port);
            System.out.println("Server UDP pronto sulla porta " + port);
        } catch (IOException e) {
            System.err.print("errore nell'ascolto");
        }
    }

    public void attendi() {
        byte[] buffer = new byte[1024];
        lastReceivedPacket = new DatagramPacket(buffer, buffer.length);
        System.out.println("In attesa di pacchetti...");
    }

    public void leggi() {
        try {
            // Il metodo receive è bloccante: il programma si ferma qui finché non arriva qualcosa
            socket.receive(lastReceivedPacket);

            String messaggio = new String(lastReceivedPacket.getData());
            System.out.println("Ricevuto: " + messaggio);
        } catch (IOException e) {
            System.err.print("errore nella lettura del messaggio");
        }
    }
    public String getMessaggio(){
        return messaggio;
    }
    public void scrivi() {
        try {
            String risposta = "Messaggio ricevuto correttamente";
            byte[] bufferRisposta = risposta.getBytes();
            InetAddress clientAddress = lastReceivedPacket.getAddress();
            int clientPort = lastReceivedPacket.getPort();
            DatagramPacket responsePacket = new DatagramPacket(bufferRisposta, bufferRisposta.length, clientAddress, clientPort);
            socket.send(responsePacket);
            System.out.println("Risposta inviata al client.");
        } catch (IOException e) {
            System.err.print("messaggio non inviato");
        }
    }
}
