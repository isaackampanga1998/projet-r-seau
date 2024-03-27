import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Serveur {

    private final int PORT = 1234; // Port pour la communication

    public void demarrer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serveur en attente de connexion...");

            // Utilisation d'un pool de threads pour gérer plusieurs connexions simultanées
            ExecutorService pool = Executors.newFixedThreadPool(1000);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connexion établie avec un client.");

                // Assigner la connexion à un thread du pool
                pool.execute(new GestionnaireClient(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
