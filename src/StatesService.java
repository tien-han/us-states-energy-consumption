import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Executes the US State Energy Consumption Protocol commands from a socket.
 */
public class StatesService implements Runnable {
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private States states;

    /**
     * Constructs a service object that processes commands from
     * a socket for states.
     * @param aSocket the socket
     * @param states the dictionary of states
     */
    public StatesService(Socket aSocket, States states) {
        s = aSocket;
        this.states = states;
    }

    public void run() {
        try {
            try {
                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream());
                doService();
            } finally {
                s.close();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Executes all commands until the QUIT command or the
     * end of input.
     * @throws IOException the exception if an operation fails
     */
    public void doService() throws IOException {
        while (true) {
            if (!in.hasNext()) {
                return;
            }
            String command = in.next();
            if (command.equals("QUIT")) {
                return;
            } else {
                executeCommand(command);
            }
        }
    }

    /**
     * Executes a single command.
     * @param command the command to execute
     */
    public void executeCommand(String command) {
        String state = in.next();

        if (command.equals("INCREASE")) {
            String energy = in.next();
            double amount = in.nextDouble();
            this.states.increase(state, energy, amount);
            out.println(state + " " + energy + " increased to " + this.states.getAmount(state, energy));
        } else if (command.equals("DECREASE")) {
            String energy = in.next();
            double amount = in.nextDouble();
            this.states.decrease(state, energy, amount);
            out.println(state + " " + energy + " decreased to " + this.states.getAmount(state, energy));
        } else if (command.equals("AMOUNT")) {
            String energy = in.next();
            out.println("The current " + energy + " consumption for " + state + " is " + this.states.getAmount(state, energy));
        } else {
            out.println("Your command " + command + " is not recognized.");
        }
        out.flush();
    }
}
