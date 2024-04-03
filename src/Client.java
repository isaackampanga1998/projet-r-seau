import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    //Attributs
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String nomUtilisateur;

    //Constructeur
    public Client(Socket socket, String nomUtilisateur) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.nomUtilisateur = nomUtilisateur;
        } catch (IOException e) {
            toutFermer(socket, bufferedWriter, bufferedReader);
        }
    }

    //Méthode pour envoyer un message au gestionnaire de clients
    public void envoyerMessage() {
        try {
            bufferedWriter.write(nomUtilisateur);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            //Écriture dans la console
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {

                //Envoi du message dans la console des autres clients
                String messageAEnvoyer = scanner.nextLine();
                bufferedWriter.write(nomUtilisateur + ": " + messageAEnvoyer);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            toutFermer(socket, bufferedWriter, bufferedReader);
        }
    }

    //Méthode pour écouter (recevoir) des messages du serveur
    public void ecouteMessage() {
        //Nouveau thread pour éviter la concurrence
        new Thread(new Runnable() {
            @Override
            public void run() {

                String msgDuGroupe;
                while(socket.isConnected()) {
                    try {
                        msgDuGroupe = bufferedReader.readLine();
                        System.out.println(msgDuGroupe);
                    } catch (IOException e) {
                        toutFermer(socket, bufferedWriter, bufferedReader);
                    }
                }
            }
        }).start();
    }

    //Méthode pour fermer la connexion
    public void toutFermer(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
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

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez entrer votre nom d'utilisateur: ");
        String nomUtilisateur = scanner.nextLine();
        Socket socket = new Socket("localhost", 1234);
        Client client = new Client(socket, nomUtilisateur);
        client.ecouteMessage();
        client.envoyerMessage();
    }
}

