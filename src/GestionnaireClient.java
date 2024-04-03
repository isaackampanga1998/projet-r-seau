import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class GestionnaireClient implements Runnable {

    //Attributs
    public static ArrayList<GestionnaireClient> gestionnairesClient = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String nomClient;

    //Constructeur
    public GestionnaireClient (Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.nomClient = bufferedReader.readLine();
            gestionnairesClient.add(this);
            envoyerMessage("SERVEUR: " + nomClient + " a entré dans le tchat!");
        } catch (IOException e) {
            toutFermer(socket, bufferedReader, bufferedWriter);
        }
    }
    //Override pour permettre d'envoyer plusieurs messages,
    //sans avoir besoin de le faire à tour de rôle
    @Override
    public void run() {
        String messageDuClient;

        //Boucle tant que le socket est connecté
        while(socket.isConnected()) {
            try {
                //Envoi du message
                messageDuClient = bufferedReader.readLine();
                envoyerMessage(messageDuClient);
            } catch (IOException e) {
                toutFermer(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    //Méthode pour l'envoi des messages
    public void envoyerMessage(String messageAEnvoyer){
        //Navigation du gestionnaire des clients
        for (GestionnaireClient clientHandler: gestionnairesClient) {
            try {
                //Si ce n'est pas le même client qui envoie le message,
                //on l'envoie à celui-ci
                if(!clientHandler.nomClient.equals((nomClient))){
                    clientHandler.bufferedWriter.write(messageAEnvoyer);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            } catch (IOException e) {
                toutFermer(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    //Méthode pour retirer un client du tchat, dans le cas ou la connexion est perdue
    public void supprimerGestionnaireClient() {
        gestionnairesClient.remove(this);
        envoyerMessage("SERVEUR: " + nomClient + " a quitté le tchat!");
    }

    //Méthode pour fermer la connexion
    public void toutFermer(Socket socket, BufferedReader bufferedWriter, BufferedWriter bufferedReader) {
        supprimerGestionnaireClient();
        try {
            if(bufferedReader != null) {
                bufferedReader.close();
            }
            if(bufferedWriter != null) {
                bufferedWriter.close();
            }
            if(socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

