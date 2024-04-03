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
        // Cette fonctionnalité sera implémentée plus tard
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
        // Cette fonctionnalité sera implémentée plus tard
    }
}

