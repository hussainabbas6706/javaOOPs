package TCPClientServer;

import java.net.*;
import java.io.*;

public class Server {

    public Server(int port) {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (true) {
                Socket s = ss.accept();
                System.out.println("Client connected: " + s.getInetAddress());

                ClientHandler ch = new ClientHandler(s);
                ch.start();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            try {
                if (ss != null)
                    ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new Server(5000);
    }
}
