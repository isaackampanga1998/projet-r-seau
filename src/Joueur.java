public class Joueur {
    private String nom;
    private int score;
    private int vies;

    public Joueur(String nom) {
        this.nom = nom;
        this.score = 10; // Début avec 10 points
        this.vies = 3; // Début avec 3 vies
    }

    // Méthodes pour accéder et mettre à jour le score et les vies du joueur
}
