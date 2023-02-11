package info.kgeorgiy.ja.bondarev.hello;

import info.kgeorgiy.java.advanced.hello.HelloServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Implementation of {@link HelloServer} interface. Server for processing requests from {@link HelloUDPClient}.
 * <p>Response is a string: "Hello, [data of request]".
 * @author Nikita Bondarev
 */
public class HelloUDPServer implements HelloServer {

    private DatagramSocket socket;
    private ExecutorService service;

    @Override
    public void start(int port, int threads) {
        try {
            socket = new DatagramSocket(port);
            service = Executors.newFixedThreadPool(threads);
            for (int i = 0; i < threads; i++) {
                service.submit(this::parallelProcessing);
            }
        } catch (SocketException e) {
            System.err.println(UDPUtilities.SOCKET_EXCEPTION_MESSAGE + e.getMessage());
        }

    }

    private void parallelProcessing() {
        while (!socket.isClosed()) {
            try {
                final DatagramPacket request = UDPUtilities.createPacketForReceiving(socket.getReceiveBufferSize());
                socket.receive(request);
                socket.send(getResponse(request));
            } catch (IOException e) {
                if (socket.isClosed()) {
                    System.out.println("Cannot process requests. Server is closed.");
                    return;
                } else {
                    System.out.println("Server processing failed: " + e.getMessage() + ". Trying again.");
                }
            }
        }

    }

    private DatagramPacket getResponse(DatagramPacket request) {
        return UDPUtilities.createPacketForSending("Hello, "
                + UDPUtilities.getData(request), request.getSocketAddress());
    }

    @Override
    public void close() {
        if (socket != null) {
            socket.close();
        }
        UDPUtilities.closeService(service);
    }
}