package network;

import network.Request;
import network.Response;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

/**
 * The type Server connection.
 */
public class ServerConnection {

    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    /**
     * Send request response.
     *
     * @param request the request
     * @return the response
     */
// ---------------- SEND REQUEST ----------------
    public Response sendRequest(Request request) {
        try (Socket socket = new Socket(HOST, PORT)) {

            // ---------------- OUTPUT STREAM ----------------
            OutputStream out = socket.getOutputStream();

            byte[] requestBytes = serialize(request);

            // length-prefix: [size][data]
            DataOutputStream dataOut = new DataOutputStream(out);
            dataOut.writeInt(requestBytes.length);
            dataOut.write(requestBytes);
            dataOut.flush();

            // ---------------- INPUT STREAM ----------------
            DataInputStream dataIn = new DataInputStream(socket.getInputStream());

            int size = dataIn.readInt(); // read length

            byte[] responseBytes = new byte[size];
            dataIn.readFully(responseBytes);

            return (Response) deserialize(responseBytes);

        } catch (ConnectException e) {
            System.out.println(" :( Server is unavailable.");
            return new Response("Server unavailable", null);

        } catch (Exception e) {
            System.out.println(" :( Error while communicating: " + e.getMessage());
            return new Response("Communication error", null);
        }
    }

    // ---------------- SERIALIZATION ----------------
    private byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(obj);
        oos.flush();

        return bos.toByteArray();
    }

    // ---------------- DESERIALIZATION ----------------
    private Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bis);

        return ois.readObject();
    }
}