package server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class ServerMain {
    public static void main(String[] args) {
        int port = 5000;
        byte[] buffer = new byte[1024];
        Server server = new Server(5000);
        try {
            ServerSocket serverSocket = new ServerSocket(port);
        }
        catch (IOException e){

        }
        server.attendi();
        server.leggi();
        server.scrivi();
    }
}