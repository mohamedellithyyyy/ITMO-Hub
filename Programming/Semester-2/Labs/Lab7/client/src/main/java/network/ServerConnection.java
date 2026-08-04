package network;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerConnection {

    private static final String HOST = System.getProperty("host", "localhost");
    private static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    private static final int TIMEOUT = 5000;

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public void connect() {
        try {
            socket = new Socket(HOST, PORT);
            socket.setSoTimeout(TIMEOUT);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            System.out.println("[DEBUG] Connected to " + HOST + ":" + PORT);
        } catch (ConnectException e) {
            System.out.println("❌ Server is unavailable");
        } catch (Exception e) {
            System.out.println("❌ Connection error: " + e.getMessage());
        }
    }

    public Response sendRequest(Request request) {
        try {
            oos.writeObject(request);
            oos.flush();
            System.out.println("[DEBUG] Request sent");

            Response response = (Response) ois.readObject();
            System.out.println("[DEBUG] Response received");
            return response;
        } catch (SocketTimeoutException e) {
            System.out.println("❌ Timeout: server did not respond");
            return new Response("Server timeout", null);
        } catch (Exception e) {
            System.out.println("❌ Communication error: " + e.getMessage());
            e.printStackTrace();
            return new Response("Communication error", null);
        }
    }

    public void disconnect() {
        try {
            if (oos != null) oos.close();
            if (ois != null) ois.close();
            if (socket != null) socket.close();
            System.out.println("[DEBUG] Disconnected");
        } catch (Exception ignored) {}
    }
}