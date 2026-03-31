package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Gestisce un server che resta in ascolto su una porta specifica.
 */
public class Server {

    private int port;
    private DatagramSocket socket;
    private DatagramPacket lastReceivedPacket;
    String messaggio = "";
    String ip;
    private String x = "";

    /**
     * Crea il server e lo mette in ascolto sulla porta.
     * @param port Numero della porta.
     */
    public Server(int port) {
        this.port = port;
        try {
            this.socket = new DatagramSocket(port);
            System.out.println("Server UDP pronto sulla porta " + port);
        } catch (IOException e) {
            System.err.print("errore nell'ascolto");
        }
    }

    /**
     * Prepara il buffer per ricevere un nuovo pacchetto.
     */
    public void attendi() {
        byte[] buffer = new byte[1024];
        lastReceivedPacket = new DatagramPacket(buffer, buffer.length);
        System.out.println("In attesa di pacchetti...");
    }

    /**
     * Riceve il pacchetto e salva IP e messaggio.
     */
    public void leggi() {
        try {
            socket.receive(lastReceivedPacket);
            this.ip = lastReceivedPacket.getAddress().getHostAddress();
            //ho aggiunto getLength per evitare i caratteri nulli nel messaggio
            String messaggio = new String(lastReceivedPacket.getData(), 0, lastReceivedPacket.getLength());
            this.messaggio = messaggio;
        } catch (IOException e) {
            System.err.print("errore nella lettura del messaggio");
        }
    }

    /**
     * Restituisce l'IP dell'ultimo mittente.
     * @return Indirizzo IP come stringa.
     */
    public String getIp(){
        return ip;
    }

    public String[] separaMessaggio(){
        return getMessaggio().split(" - ");
    }

    public String getNome(){
        return separaMessaggio()[0];
    }
    /**
     * Restituisce l'ultimo messaggio ricevuto.
     * @return Testo del messaggio.
     */
    public String getMessaggio(){
        return messaggio;
    }

    public String getMessaggioSeparato(){
        return separaMessaggio()[1].trim();
    }

    /**
     * Invia una conferma di ricezione al mittente.
     */
    public void scrivi(String x) {
        try {
            byte[] bufferRisposta = x.getBytes();
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
