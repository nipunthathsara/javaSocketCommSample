package org.wso2.tcp.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * TCP Server class
 */
public class Server {
    private int PORT;
    private Socket client;
    private ServerSocket ss;
    private ExecutorService executor;
    private int SIZE ,MIN, MAX;

    /**
     *
     * @param port
     * @param queueSize
     * @param min
     * @param max
     */
    public Server(int port, int queueSize, int min, int max) {
        this.MAX = max;
        this.MIN = min;
        this.SIZE = queueSize;
        this.PORT = port;

        //queue to handle requests
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue(SIZE);
        executor = new ThreadPoolExecutor(this.MIN, this.MAX, 1000, TimeUnit.MILLISECONDS, blockingQueue);
    }

    /**
     *
     * @throws IOException
     */
    public void startServer() throws IOException {
        ss = new ServerSocket(PORT);
        int poolSize = 10;
        System.out.println("Listening on: " + PORT);

        while (true) {
            client = ss.accept();
            System.out.println("Client connected...");
            executor.execute(new ClientThread(client));
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //PORT,Queue size, Min, Max - arguments
        Server server = new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        try {
            server.startServer();
        } catch (IOException e) {
            System.out.println("Server stopped responding");
            e.printStackTrace();
        }
    }
}
