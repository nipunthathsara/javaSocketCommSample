package org.wso2.samples;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static int PORT = 9000;
    static Socket client;
    static ServerSocket ss;

    public static void main(String[] args) throws IOException {
        ss = new ServerSocket(PORT);
        System.out.println("Listening on: " + PORT);
        client = ss.accept();
        System.out.println("connected to a client");

        DataInputStream dataInputStream = new DataInputStream(client.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(client.getOutputStream());

        int number = dataInputStream.readInt();
        dataOutputStream.writeInt(number * 2);

        System.out.println("Client processed");
    }
}