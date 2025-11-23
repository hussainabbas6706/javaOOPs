package TCPClientServer;

import java.io.*;
import java.net.*;

public class Client {

    private Socket s = null;
    private BufferedReader consoleInput = null; // for reading from console
    private BufferedWriter out = null; // for sending to server

    public Client(String addr, int port) {
        try {
            s = new Socket(addr, port);
            System.out.println("Connected to server at " + addr + ":" + port);

            // Console input
            consoleInput = new BufferedReader(new InputStreamReader(System.in)); 

            // Output to server
            out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        } catch (UnknownHostException u) {
            System.out.println(u);
            return;
        } catch (IOException i) {
            System.out.println(i);
            return;
        }

        String message = "";

        try {
            while (!message.equalsIgnoreCase("Quit")) {
                System.out.print("Enter message: ");
                message = consoleInput.readLine(); // read from console

                // send to server with newline
                out.write(message);
                out.newLine();
                out.flush();
            }
        } catch (IOException i) {
            System.out.println(i);
        } finally {
            // Close resources
            try {
                if (consoleInput != null)
                    consoleInput.close();
                if (out != null)
                    out.close();
                if (s != null)
                    s.close();
            } catch (IOException i) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        new Client("127.0.0.1", 5000);
    }

}
