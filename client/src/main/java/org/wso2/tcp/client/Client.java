package org.wso2.tcp.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * TCP Client class
 */
public class Client {

    private static int PORT;
    private static String host = "localhost";
    private static Socket connection;

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        PORT = Integer.parseInt(args[0]);
        connection = new Socket(host, PORT);
        DataInputStream dataInputStream = new DataInputStream(connection.getInputStream());
        //read from server and output
        System.out.println("Server response: " + dataInputStream.readInt());
    }
}
