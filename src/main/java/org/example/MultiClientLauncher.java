package org.example;

public class MultiClientLauncher {
    public static void main(String[] args) {

        int numClients = 3;
        int interval = 200;
        int maxMessages = 100;

        for (int i = 1; i <= numClients; i++) {
            String clientName = "Cliente-" + i;
            Thread clientThread = new Thread(new ClientInstance(clientName, interval, maxMessages));
            clientThread.start();
        }
    }
}
