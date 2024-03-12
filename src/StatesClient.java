import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * This program tests the state statistics server.
 */
public class StatesClient {
    public static void main(String[] args) throws IOException {
        final int SBAP_PORT = 8888;

        try (Socket s = new Socket("localhost", SBAP_PORT)) {
            InputStream instream = s.getInputStream();
            OutputStream outstream = s.getOutputStream();
            Scanner in = new Scanner(instream);
            PrintWriter out = new PrintWriter(outstream);

            String command = "INCREASE WA coal 100";
            System.out.println("Sending: " + command);
            out.print(command + "\n"); //Sends command out to the socket
            out.flush(); //Do it right now
            String response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "DECREASE WA coal 50";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "AMOUNT WA coal";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "AMOUNT WA solar";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            //Test an invalid command
            command = "HELLO WA solar";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
            response = in.nextLine();
            System.out.println("Receiving: " + response);

            command = "QUIT";
            System.out.println("Sending: " + command);
            out.print(command + "\n");
            out.flush();
        }
    }
}
