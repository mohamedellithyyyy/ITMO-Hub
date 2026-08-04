package network;

import database.MusicBandDAO;
import managers.CollectionManager;
import threading.ThreadPoolManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ForkJoinPool;

public class Server {
    private static final int PORT = 8080;
    private final RequestHandler requestHandler;

    public Server(CollectionManager collectionManager, MusicBandDAO musicBandDAO) {
        this.requestHandler = new RequestHandler(collectionManager, musicBandDAO);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);
            ForkJoinPool readingPool = ThreadPoolManager.getReadingPool();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getRemoteSocketAddress());

                readingPool.execute(() -> handleClient(clientSocket));
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

            while (true) {
                Object obj;
                try {
                    obj = ois.readObject();
                } catch (EOFException e) {
                    break; // client closed connection
                }
                if (!(obj instanceof Request)) break;
                Request request = (Request) obj;
                System.out.println("Received: " + request.getCommandType());

                ThreadPoolManager.getProcessingPool().submit(() -> {
                    Response response = requestHandler.handle(request);
                    // ⚡ Thread‑safe send: synchronize on the client's oos
                    ThreadPoolManager.sendResponseAsync(() -> {
                        synchronized (oos) {
                            try {
                                oos.writeObject(response);
                                oos.flush();
                                System.out.println("Response sent");
                            } catch (IOException e) {
                                System.err.println("Send error: " + e.getMessage());
                            }
                        }
                    });
                });
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading request: " + e.getMessage());
        } finally {
            try { clientSocket.close(); } catch (IOException ignored) {}
        }
    }
}