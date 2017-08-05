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
        this.MAX = max;//maximum number of threads in pool
        this.MIN = min;//minimum number of threads in pool
        this.SIZE = queueSize;//size of the client queue
        this.PORT = port;//server port

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
        System.out.println("Listening on: " + PORT);

        while (true) {
            client = ss.accept();
            System.out.println("Client connected...");
            executor.execute(new ClientThread(client));
        }
    }

    /**
     * Method to shutdown the server
     */
    /**
     * TODO Invoke this method by shell script
     */
//    Couldn't invoke by bash script, hence server will not shutdown when started by the script.
//    Search for process id: sudo ss -lptn 'sport = :<PORT>
//    Kill the process manually: sudo kill -9 <PID>
    public void stopServer(){
        //Terminating the thread poolb
        try {
            if (this.executor.awaitTermination(2, TimeUnit.SECONDS)) {
                System.out.println("Client processes terminated successfully");
            } else {
                System.out.println("Forcing terminating client processes...");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("Problem terminating the client processes... " + e);
        }
        //Closing socket connections
        try {
            this.client.close();
            this.ss.close();
            System.out.println("Server shutdown successful!");
        } catch (IOException e) {
            System.out.println("Problem shutting down the server..." + e);
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //Arguments - PORT,Queue size, Min, Max
        Server server = new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        try {
            server.startServer();
        } catch (IOException e) {
            System.out.println("Server stopped responding");
            e.printStackTrace();
        }
    }
}
