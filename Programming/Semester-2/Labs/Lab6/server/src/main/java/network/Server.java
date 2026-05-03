package network;

import commands.CommandProcessor;
import managers.CollectionManager;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Stable NIO Server for Lab6 (Helios-safe version)
 */
public class Server {

    private static final int PORT = 8080;

    private Selector selector;
    private ServerSocketChannel serverChannel;

    private final CommandProcessor commandProcessor;

    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public Server(CollectionManager collectionManager) {
        this.commandProcessor = new CommandProcessor(collectionManager);
    }

    // ================= LOGGING =================
    private void setupLogging() {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());

        logger.addHandler(handler);
        logger.setUseParentHandlers(false);
    }

    // ================= START SERVER =================
    public void start() {
        setupLogging();

        try {
            selector = Selector.open();

            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);

            // IMPORTANT: bind properly (external access allowed)
            serverChannel.bind(new InetSocketAddress(PORT));

            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            logger.info("Server started on port " + PORT);

            // IMPORTANT: keep server alive forever
            while (serverChannel.isOpen()) {

                selector.select();

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    try {
                        if (key.isAcceptable()) {
                            acceptClient();
                        }

                        if (key.isReadable()) {
                            readRequest(key);
                        }
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "Key processing error", e);
                        try {
                            key.channel().close();
                        } catch (IOException ignored) {}
                    }
                }
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Fatal server error", e);
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

    // ================= ACCEPT CLIENT =================
    private void acceptClient() throws IOException {
        SocketChannel client = serverChannel.accept();

        if (client == null) return;

        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);

        logger.info("Client connected: " + client.getRemoteAddress());
    }

    // ================= READ REQUEST =================
    private void readRequest(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();

        try {
            logger.info("Reading request from client");

            ByteBuffer sizeBuffer = ByteBuffer.allocate(4);

            int read = client.read(sizeBuffer);
            if (read == -1) {
                client.close();
                logger.info("Client disconnected");
                return;
            }

            if (read < 4) return;

            sizeBuffer.flip();
            int size = sizeBuffer.getInt();

            ByteBuffer dataBuffer = ByteBuffer.allocate(size);
            int totalRead = 0;

            while (totalRead < size) {
                int r = client.read(dataBuffer);

                if (r == -1) {
                    client.close();
                    logger.info("Client disconnected during request");
                    return;
                }

                totalRead += r;
            }

            dataBuffer.flip();
            byte[] data = new byte[size];
            dataBuffer.get(data);

            Request request = deserialize(data);

            logger.info("Request received");

            Response response = commandProcessor.execute(request);

            sendResponse(client, response);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Client handling error", e);
            try {
                client.close();
            } catch (IOException ignored) {}
        }
    }

    // ================= SEND RESPONSE =================
    private void sendResponse(SocketChannel client, Response response) throws IOException {

        logger.info("Sending response to client");

        byte[] responseBytes = serialize(response);

        ByteBuffer buffer = ByteBuffer.allocate(4 + responseBytes.length);
        buffer.putInt(responseBytes.length);
        buffer.put(responseBytes);
        buffer.flip();

        while (buffer.hasRemaining()) {
            client.write(buffer);
        }

        logger.info("Response sent");
    }

    // ================= SERIALIZATION =================
    private byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(obj);
        oos.flush();

        return bos.toByteArray();
    }

    private Request deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bis);

        return (Request) ois.readObject();
    }

    // ================= CLEAN SHUTDOWN =================
    private void shutdown() {
        try {
            if (serverChannel != null) serverChannel.close();
            if (selector != null) selector.close();
            logger.info("Server shutdown complete");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Shutdown error", e);
        }
    }
}