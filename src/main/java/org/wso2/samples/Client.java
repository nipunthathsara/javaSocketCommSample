package org.wso2.samples;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static int PORT = 9000;
    private static String host = "localhost";
    private static Socket connection;

    public static void main(String[] args) throws IOException {
        connection = new Socket(host, PORT);

        DataInputStream dataInputStream = new DataInputStream(connection.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number: ");
        dataOutputStream.writeInt(scanner.nextInt());
        System.out.println("Server response: " + dataInputStream.readInt());
    }
}