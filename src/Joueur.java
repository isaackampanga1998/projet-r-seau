public class Joueur {
    private String nom;
    private int points;
    private int vies;

    public Joueur(String nom) {
        this.nom = nom;
        this.points = 10; // Début avec 10 points
        this.vies = 3; // Début avec 3 vies
    }

   public void miseSurGrille(int[] coordonnees) {
    // Méthode pour placer une mise sur la grille
    
    // Vérifier si les coordonnées sont valides
    if (coordonnees[0] >= 0 && coordonnees[0] < tailleGrille && coordonnees[1] >= 0 && coordonnees[1] < tailleGrille) {
        // Placer la mise sur la grille
        grille[coordonnees[0]][coordonnees[1]] = 'X'; // 'X' représente la mise du joueur sur la grille
        System.out.println(nom + " a placé une mise sur la grille aux coordonnées : (" + coordonnees[0] + ", " + coordonnees[1] + ")");
    } else {
        System.out.println("Coordonnées invalides.");
    }
}

    public void ajusterPoints(int points) {
        // Méthode pour ajuster les points du joueur
        this.points += points;
    }

    public void perdreVie() {
        // Méthode pour perdre une vie
        this.vies--;
    }

    public void envoyerMessage(String message) {
    // Méthode pour envoyer un message aux autres joueurs
    // Pour l'instant, nous utilisons la communication déjà mise en place à l'aide de la classe Client
    // Nous créons un client fictif pour envoyer le message, mais en réalité, cette méthode sera appelée lorsqu'un joueur veut envoyer un message aux autres joueurs
    Client client = new Client(null, nom); // Création d'un client fictif pour envoyer un message
    client.envoyerMessage(message);
}

    public String getPoints() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPoints'");
    }

    public String getNom() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNom'");
    }
}
 public void jouer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("C'est à votre tour de jouer, " + nom + " !");
        System.out.println("Entrez les coordonnées (x, y) où vous pensez que le chat va dormir :");
        System.out.print("Coordonnée x : ");
        int x = scanner.nextInt();
        System.out.print("Coordonnée y : ");
        int y = scanner.nextInt();
        
        // Simulation de la mise sur la grille
        int[] coordonnees = {x, y};
        miseSurGrille(coordonnees);

        // Simulation du calcul des points
        if (x == 2 && y == 2) {
            ajusterPoints(10);
        } else {
            ajusterPoints(-1);
        }

        // Affichage des points mis à jour
        System.out.println("Points de " + nom + ": " + getPoints());
    }
}
