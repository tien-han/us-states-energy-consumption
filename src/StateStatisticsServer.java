import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A server that executes the US State Energy Consumption Protocol.
 */
public class StateStatisticsServer {
    public static void main(String[] args) throws IOException {
        States states = new States();
        final int SBAP_PORT = 8888;
        ServerSocket server = new ServerSocket(SBAP_PORT);
        System.out.println("Waiting for user to connect...");

        while (true) {
            Socket s = server.accept();
            System.out.println("User connected.");
            StatesService service = new StatesService(s, states);
            Thread t = new Thread(service);
            t.start();
        }
    }
}
