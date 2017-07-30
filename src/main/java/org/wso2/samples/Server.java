package org.wso2.samples;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static int PORT;
    static Socket client;
    static ServerSocket ss;
    static ExecutorService executor;

    public Server (int poolSize, int port){
        PORT = port;
        executor = Executors.newFixedThreadPool(poolSize);
    }

    public void startServer()throws IOException{
        ss = new ServerSocket(PORT);
        int poolSize = 10;
        System.out.println("Listening on: " + PORT);

        while (true){
            client = ss.accept();
            System.out.println("Client connected...");
            executor.execute(new ClientThread(client));
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5, 9009);
        try {
            server.startServer();
        } catch (IOException e) {
            System.out.println("Server stopped working");
            e.printStackTrace();
        }
    }
}