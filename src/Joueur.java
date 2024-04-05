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
        // Cette fonctionnalité sera implémentée plus tard
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

