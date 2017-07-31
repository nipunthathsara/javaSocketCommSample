package org.wso2.samples;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Server {
    private static int PORT;
    static Socket client;
    static ServerSocket ss;
    static ExecutorService executor;
    static int SIZE;
    static int MIN;
    static int MAX;
    public Server (int poolSize, int PORT, int SIZE, int MIN, int MAX){
        Server.MAX = MAX;
        Server.MIN=MIN;
        Server.SIZE = SIZE;
        Server.PORT = PORT;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue(SIZE);
        //executor = Executors.newFixedThreadPool(poolSize);
        executor = new ThreadPoolExecutor(MIN, MAX, 1000, TimeUnit.MILLISECONDS, blockingQueue);
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
        Server server = new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
        //poolSize, PORT, SIZE, MAX - pass these args to main
        //MIN - hardcoded
        try {
            server.startServer();
        } catch (IOException e) {
            System.out.println("Server stopped responding");
            e.printStackTrace();
        }
    }
}