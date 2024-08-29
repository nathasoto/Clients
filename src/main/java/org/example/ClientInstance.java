package org.example;
import java.io.*;
import java.net.*;
public class ClientInstance implements Runnable {
    private String clientName;
    private int interval;
    private int maxMessages;

    public ClientInstance(String clientName, int interval, int maxMessages) {
        this.clientName = clientName;
        this.interval = interval;
        this.maxMessages = maxMessages;

    }

    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 6666);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {


            out.println(clientName);
            System.out.println(clientName + " connected to the server");

            for (int i = 1; i <= maxMessages; i++) {
                String message = clientName + ": Message " + i;
                out.println(message);
                System.out.println(clientName + " sent: " + message);

                String response = in.readLine();
                if (response != null) {
                    System.out.println(clientName + " received: " + response);
                }

                Thread.sleep(interval);
            }

            System.out.println(clientName + " has finished sending messages.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}