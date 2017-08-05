package org.wso2.tcp.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Class to process each client
 */
public class ClientThread extends Thread {
    private Socket client;

    /**
     * @param client
     */
    public void setClient(Socket client) {
        this.client = client;
    }

    public ClientThread() {}

    /**
     * @param client
     */
    public ClientThread(Socket client) {//remove this cons.
        this.client = client;
    }

    public void run() {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(this.client.getOutputStream());
            dataOutputStream.writeInt(999999999);
            System.out.println("Client processed");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
