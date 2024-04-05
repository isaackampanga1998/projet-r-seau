import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Veuillez entrer le nombre de joueurs (1 à 5): ");
        int nombreJoueurs = scanner.nextInt();
        String[] nomsDesJoueurs = new String[nombreJoueurs];

        // Saisir les noms des joueurs
        for (int i = 0; i < nombreJoueurs; i++) {
            System.out.print("Entrez le nom du joueur " + (i + 1) + ": ");
            nomsDesJoueurs[i] = scanner.next();
        }

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Serveur serveur = new Serveur(serverSocket);
            serveur.demarrerServeur();

            Socket socket = new Socket("localhost", PORT);
            Client client = new Client(socket, nomsDesJoueurs[0]); // Utilisation du premier joueur pour se connecter
            client.ecouteMessage();
            client.envoyerMessage();

            // Attendre quelques secondes pour que le serveur se lance
            Thread.sleep(2000);

            // Démarrer le jeu avec les joueurs fournis
            startGameWithPlayers(nomsDesJoueurs);

            // Fermeture des connexions
            client.toutFermer(socket, client.getBufferedWriter(), client.getBufferedWriter());
            serveur.fermerSocketServeur();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void startGameWithPlayers(String[] nomsDesJoueurs) {
        Scanner scanner = new Scanner(System.in);

        // Initialisation du plateau
        Plateau plateau = new Plateau(20, 40);
        plateau.placerChat();

        System.out.println("Bienvenue dans le jeu Chat Paresseux !");
        System.out.println("Trouvez où le chat va dormir sur le plateau de jeu.");

        // Boucle de jeu
        while (true) {
            for (String nomJoueur : nomsDesJoueurs) {
                System.out.println("C'est au tour de " + nomJoueur + " de jouer.");

                // Demander les coordonnées au joueur
                System.out.println("Veuillez entrer les coordonnées (x, y) où vous pensez que le chat va dormir :");
                System.out.print("Coordonnée x : ");
                int x = scanner.nextInt();
                System.out.print("Coordonnée y : ");
                int y = scanner.nextInt();

                // Placer la mise du joueur sur le plateau et calculer les points
                Joueur joueur = new Joueur(nomJoueur);
                plateau.placerMise(joueur, x, y);
                plateau.calculerPoints(joueur, x, y);
                plateau.afficherGrille();

                // Afficher les points du joueur
                System.out.println("Points du joueur " + joueur.getNom() + ": " + joueur.getPoints());

                // Vérifier si le joueur souhaite continuer à jouer
                System.out.println("Voulez-vous continuer à jouer ? (Oui/Non)");
                String reponse = scanner.next().toLowerCase();
                if (!reponse.equals("oui")) {
                    return; // Fin du jeu si le joueur ne veut pas continuer
                }
            }
        }
    }
}
