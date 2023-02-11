package info.kgeorgiy.ja.bondarev.hello;

import java.net.DatagramPacket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Auxiliary class for {@link HelloUDPClient} and {@link HelloUDPServer}.
 * @author Nikita Bondarev
 */
public class UDPUtilities {

    /**
     * Message for {@link SocketException}.
     */
    public static final String SOCKET_EXCEPTION_MESSAGE = "Cannot create a socket: ";

    /**
     * Amount of milliseconds a call to receive() for DatagramSocket will block for
     */
    public static final int TIMEOUT = 10;

    /**
     * Creates {@link DatagramPacket a packet} for receiving packets with given size.
     * @param size size of bytes buffer to read.
     * @return new instance of DatagramPacket.
     */
    public static DatagramPacket createPacketForReceiving(int size) {
        return new DatagramPacket(new byte[size], size);
    }

    /**
     * Creates {@link DatagramPacket a packet} for sending packets with given data to given server.
     * @param data string to be sent.
     * @param server address of server where {@code data} will be send to.
     * @return new instance of DatagramPacket.
     */
    // :NOTE: Кодировка
    public static DatagramPacket createPacketForSending(String data, SocketAddress server) {
        return new DatagramPacket(data.getBytes(), data.getBytes().length, server);
    }

    /**
     * Extracts data from given {@link DatagramPacket}.
     * @param packet packet where data contains.
     * @return extracted data.
     */
    public static String getData(DatagramPacket packet) {
        return new String(packet.getData(), packet.getOffset(), packet.getLength());
    }

    /**
     * Close given {@link ExecutorService}.
     * @param executorService service to be closed.
     */
    public static void closeService(ExecutorService executorService) {
        if (executorService == null) {
            return;
        }
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
                System.out.println("Service termination is taking too much time. It's forcibly stopped.");
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted while waiting termination of this service: " + e.getMessage());
        }
        executorService.shutdownNow();
    }
}