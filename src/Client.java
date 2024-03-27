import java.io.*;
import java.net.*;

public class Client {
    private final String HOST = "localhost"; // Adresse IP du serveur
    private final int PORT = 1234; // Port de communication avec le serveur

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client() {
        try {
            socket = new Socket(HOST, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthodes pour la communication avec le serveur
}

