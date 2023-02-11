package info.kgeorgiy.ja.bondarev.hello;

import info.kgeorgiy.java.advanced.hello.HelloClient;
import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * Implementation of {@link HelloClient} interface. Client for sending requests to {@link HelloUDPServer}.
 * <p>Request is a string: "{@code prefix} + {@code indexOfThread} + {@code _} + {@code indexOfRequest}".
 *
 * @author Nikita Bondarev
 */
public class HelloUDPClient implements HelloClient {

    @Override
    public void run(String name, int port, String prefix, int threads, int requests) {
        final ExecutorService service = Executors.newFixedThreadPool(threads);
        final SocketAddress server = new InetSocketAddress(name, port);
        Phaser phaser = new Phaser(1);
        for (int i = 0; i < threads; i++) {
            final int indexOfThread = i;
            service.submit(() -> parallelProcessing(phaser, server, prefix, indexOfThread, requests));
        }
        phaser.arriveAndAwaitAdvance();
        UDPUtilities.closeService(service);
    }

    private void parallelProcessing(Phaser phaser, SocketAddress server, String prefix, int indexOfThread, int requests) {
        phaser.register();
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.connect(server);
            socket.setSoTimeout(UDPUtilities.TIMEOUT);
            for (int i = 0; i < requests; i++) {
                final String requestData = prefix + indexOfThread + "_" + i;
                process(socket,
                        UDPUtilities.createPacketForSending(requestData, server),
                        UDPUtilities.createPacketForReceiving(socket.getReceiveBufferSize()),
                        requestData);
            }
        } catch (SocketException e) {
            System.err.println(UDPUtilities.SOCKET_EXCEPTION_MESSAGE + e.getMessage());
        }
        phaser.arrive();
    }

    private void process(DatagramSocket socket, DatagramPacket request, DatagramPacket response, String data) {
        boolean success = false;
        while (!success) {
            try {
                socket.send(request);
                socket.receive(response);
                String responseData = UDPUtilities.getData(response);
                if (responseData.contains(data)) {
                    success = true;
                    System.out.println(responseData);
                }
            } catch (IOException e) {
                System.out.println("Client processing failed: " + e.getMessage() + ". Trying again.");
            }
        }
    }
}