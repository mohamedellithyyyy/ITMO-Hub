package network;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * The type Request reader.
 */
public class RequestReader {

    /**
     * Read request.
     *
     * @param channel the channel
     * @return the request
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public Request read(SocketChannel channel) throws IOException, ClassNotFoundException {

        // 1. Read 4 bytes (message size)
        ByteBuffer sizeBuffer = ByteBuffer.allocate(4);

        int read = channel.read(sizeBuffer);

        if (read == -1) {
            throw new IOException("Client disconnected");
        }

        if (read < 4) {
            throw new IOException("Failed to read message size");
        }

        sizeBuffer.flip();
        int size = sizeBuffer.getInt();

        // 2. Read full message
        ByteBuffer dataBuffer = ByteBuffer.allocate(size);
        int totalRead = 0;

        while (totalRead < size) {
            int r = channel.read(dataBuffer);

            if (r == -1) {
                throw new IOException("Client disconnected during message transfer");
            }

            totalRead += r;
        }

        dataBuffer.flip();

        byte[] data = new byte[size];
        dataBuffer.get(data);

        // 3. Deserialize Request
        return deserialize(data);
    }

    // ---------------- helper ----------------
    private Request deserialize(byte[] data) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bis)) {

            return (Request) ois.readObject();
        }
    }
}