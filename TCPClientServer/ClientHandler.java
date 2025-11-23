package TCPClientServer;

import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream()));

            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Client says: " + msg);

                out.write("Server: " + msg);
                out.newLine();
                out.flush();
            }

            socket.close();
            

        } catch (IOException e) {
            System.out.println("Client disconnected.");
        }
    }
}
