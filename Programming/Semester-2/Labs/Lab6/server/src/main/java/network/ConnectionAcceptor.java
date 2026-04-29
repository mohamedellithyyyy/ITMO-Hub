package network;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * The type Connection acceptor.
 */
public class ConnectionAcceptor {

    private final Selector selector;
    private final ServerSocketChannel serverChannel;

    /**
     * Instantiates a new Connection acceptor.
     *
     * @param selector      the selector
     * @param serverChannel the server channel
     */
    public ConnectionAcceptor(Selector selector, ServerSocketChannel serverChannel) {
        this.selector = selector;
        this.serverChannel = serverChannel;
    }

    /**
     * Accept.
     *
     * @throws IOException the io exception
     */
    public void accept() throws IOException {

        // 1. Accept new client
        SocketChannel clientChannel = serverChannel.accept();

        if (clientChannel == null) {
            return;
        }

        // 2. Non-blocking mode
        clientChannel.configureBlocking(false);

        // 3. Register for READ events
        clientChannel.register(selector, SelectionKey.OP_READ);

        // 4. Log connection
        SocketAddress remoteAddress = clientChannel.getRemoteAddress();
        System.out.println("Client connected: " + remoteAddress);
    }
}