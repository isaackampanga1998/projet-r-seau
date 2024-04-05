import java.util.Random;

public class Plateau {
    private char[][] grille;
    private int taille;
    private Random rand;

    public Plateau(int tailleMin, int tailleMax) {
        // Initialisation du plateau avec une taille aléatoire entre tailleMin et tailleMax
        this.taille = new Random().nextInt(tailleMax - tailleMin + 1) + tailleMin;
        this.grille = new char[this.taille][this.taille];
        this.rand = new Random();
    }

    public void placerChat() {
        // Méthode pour placer le chat aléatoirement sur la grille
        int x = rand.nextInt(taille);
        int y = rand.nextInt(taille);
        grille[x][y] = 'C'; // 'C' représente la position du chat
    }

    public void placerMise(Joueur joueur, int x, int y) {
        // Méthode pour placer une mise sur la grille
        // Vérifier si les coordonnées sont valides
        if (x >= 0 && x < taille && y >= 0 && y < taille) {
            // Placer la mise sur la grille
            grille[x][y] = 'M'; // 'M' représente la mise du joueur
        } else {
            System.out.println("Coordonnées invalides.");
        }
    }

    public void calculerPoints(Joueur joueur, int x, int y) {

    // Méthode pour calculer les points du joueur en fonction de sa mise
    // Cette fonctionnalité sera implémentée plus tard
    // Exemple d'implémentation simplifiée :
    if (grille[x][y] == 'C') {
        joueur.ajusterPoints(10); // Joueur trouve l'emplacement exact
    } else {
        joueur.ajusterPoints(-1); // Joueur perd un point car il n'a pas trouvé l'emplacement exact
    }
}

    public void afficherGrille() {
        // Méthode pour afficher la grille du plateau
        for (char[] ligne : grille) {
            for (char c : ligne) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
