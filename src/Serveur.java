import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Serveur {

    //Attribut socket
    private ServerSocket serverSocket;

    //Constructeur
    public Serveur(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    //Méthode pour démarrer le serveur
    public void demarrerServeur() {

        try {

            //Boucle pour faire rouler le serveur jusqu'à la fermeture du socket
            while (!serverSocket.isClosed()) {

                //Le socket attend une connexion
                Socket socket = serverSocket.accept();
                System.out.println("Un nouveau client s'est connecté");

                //Permet le multithreading des clients
                GestionnaireClient clientHandler = new GestionnaireClient(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            fermerSocketServeur();
        }
    }

    //Méthode qui ferme le socket dans le cas d'une erreur
    public void fermerSocketServeur() {
        try {
            if(serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Serveur serveur = new Serveur(serverSocket);
        serveur.demarrerServeur();
    }
}
