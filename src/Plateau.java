import java.util.Random;

public class Plateau {
    private int[][] grille;
    private int taille;

    public Plateau(int taille) {
        this.taille = taille;
        this.grille = new int[taille][taille];
        genererPlateau();
    }

    private void genererPlateau() {
        Random random = new Random();
        // Générer le plateau aléatoirement
        // Placer le chat à une position aléatoire
    }

    public boolean estPositionValide(int x, int y) {
        // Vérifier si la position (x, y) est valide sur le plateau
        return x >= 0 && x < taille && y >= 0 && y < taille;
    }

    // Autres méthodes pour accéder à la grille, placer des joueurs, etc.
}

