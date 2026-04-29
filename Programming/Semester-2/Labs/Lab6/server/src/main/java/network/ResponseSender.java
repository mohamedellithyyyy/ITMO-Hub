package network;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * The type Response sender.
 */
public class ResponseSender {

    /**
     * Send.
     *
     * @param channel  the channel
     * @param response the response
     * @throws IOException the io exception
     */
    public void send(SocketChannel channel, Response response) throws IOException {

        // 1. Serialize response to bytes
        byte[] data = serialize(response);

        // 2. Create buffer: [size][data]
        ByteBuffer buffer = ByteBuffer.allocate(4 + data.length);

        buffer.putInt(data.length);  // length prefix
        buffer.put(data);            // actual data

        buffer.flip();

        // 3. Write fully to channel
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
    }

    // ---------------- helper ----------------
    private byte[] serialize(Response response) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            oos.writeObject(response);
            oos.flush();

            return bos.toByteArray();
        }
    }
}