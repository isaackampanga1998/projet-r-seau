import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Serveur {

    // Numéro de port utilisé par le serveur
    private static final int PORT = 1234;
    // Taille du pool de threads pour gérer les connexions clientes
    private static final int THREAD_POOL_SIZE = 10;

    // Socket serveur
    private ServerSocket serverSocket;
    // Service d'exécution pour gérer les threads
    private ExecutorService executorService;

    // Constructeur
    public Serveur(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        // Création d'un pool de threads avec une taille fixe
        this.executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    // Méthode pour démarrer le serveur
    public void demarrerServeur() {
        try {
            // Boucle pour gérer les connexions jusqu'à ce que le socket soit fermé
            while (!serverSocket.isClosed()) {
                // Attente de la connexion d'un client
                Socket socket = serverSocket.accept();
                System.out.println("Un nouveau client s'est connecté");

                // Création d'un gestionnaire de client pour gérer la connexion
                GestionnaireClient clientHandler = new GestionnaireClient(socket);
                // Soumission du gestionnaire de client au pool de threads pour exécution
                executorService.submit(clientHandler);
            }
        } catch (IOException e) {
            // Gestion des erreurs lors de l'acceptation de la connexion
            System.err.println("Erreur lors de l'acceptation de la connexion : " + e.getMessage());
            fermerSocketServeur();
        }
    }

    // Méthode pour fermer proprement le socket serveur et le pool de threads
    public void fermerSocketServeur() {
        try {
            // Fermeture du socket serveur s'il est ouvert
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            // Arrêt du pool de threads
            if (executorService != null && !executorService.isShutdown()) {
                executorService.shutdown();
            }
        } catch (IOException e) {
            // Gestion des erreurs lors de la fermeture du serveur
            System.err.println("Erreur lors de la fermeture du serveur : " + e.getMessage());
        }
    }

    // Méthode principale pour démarrer le serveur
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            // Création du serveur avec le socket serveur
            Serveur serveur = new Serveur(serverSocket);
            // Démarrage du serveur
            serveur.demarrerServeur();
        } catch (IOException e) {
            // Gestion des erreurs lors du démarrage du serveur
            System.err.println("Erreur lors du démarrage du serveur : " + e.getMessage());
        }
    }
}
