package network;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerConnection {

    private static final String HOST =
            System.getProperty("host", "helios.cs.ifmo.ru");

    private static final int PORT =
            Integer.parseInt(System.getProperty("port", "8080"));

    private static final int TIMEOUT = 5000; // 5 seconds

    // ================= SEND REQUEST =================
    public Response sendRequest(Request request) {

        System.out.println("[DEBUG] Connecting to " + HOST + ":" + PORT);

        try (Socket socket = new Socket(HOST, PORT)) {

            socket.setSoTimeout(TIMEOUT);

            System.out.println("[DEBUG] Connected to server");

            // ---------------- SEND ----------------
            byte[] requestBytes = serialize(request);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(requestBytes.length);
            out.write(requestBytes);
            out.flush();

            System.out.println("[DEBUG] Request sent (" + requestBytes.length + " bytes)");

            // ---------------- RECEIVE ----------------
            DataInputStream in = new DataInputStream(socket.getInputStream());

            int size = in.readInt();
            if (size <= 0) {
                System.out.println("[DEBUG] Invalid response size: " + size);
                return new Response("Invalid server response", null);
            }

            byte[] responseBytes = new byte[size];
            in.readFully(responseBytes);

            System.out.println("[DEBUG] Response received (" + size + " bytes)");

            return (Response) deserialize(responseBytes);

        } catch (SocketTimeoutException e) {
            System.out.println("❌ Timeout: server did not respond");
            return new Response("Server timeout", null);

        } catch (ConnectException e) {
            System.out.println("❌ Server is unavailable");
            return new Response("Server unavailable", null);

        } catch (Exception e) {
            System.out.println("❌ Communication error: " + e.getMessage());
            return new Response("Communication error", null);
        }
    }

    // ================= SERIALIZATION =================
    private byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.flush();
        return bos.toByteArray();
    }

    // ================= DESERIALIZATION =================
    private Object deserialize(byte[] data)
            throws IOException, ClassNotFoundException {

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }
}