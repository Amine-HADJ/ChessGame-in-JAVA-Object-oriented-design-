package model;


import java.io.Serializable;

public abstract class AbstractPiece implements Pieces, Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected Couleur couleur;
    protected Coord coord;

    public AbstractPiece(Couleur couleur, Coord coord) {
        this.couleur = couleur;
        this.coord = coord;
    }

    @Override
    public int getX() {
        return this.coord.x;
    }

    @Override
    public int getY() {
        return this.coord.y;
    }

    @Override
    public Couleur getCouleur() {
        return this.couleur;
    }

    @Override
    public boolean move(int x, int y) {
        if (isMoveOk(x, y)) {
            coord.x = x;
            coord.y = y;
            return true;
        }
        return false;
    }

    @Override
    public boolean capture() {
        // à compléter
        return false;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [" + getCouleur() + "] [" + getX() + ", " + getY() + "]";
    }


    @Override
    public abstract boolean isMoveOk(int xFinal, int yFinal);
    
    public static void main(String[] args) {
        // création d'une tour noire en (0, 0)
        Pieces maTour = new Tour(Couleur.NOIR, new Coord(0, 0));
        
        // test de la méthode ToString()
        System.out.println(maTour);

        // test de la méthode getCouleur()
        System.out.println("Couleur de la tour : " + maTour.getCouleur());

        // test de la méthode getX()
        System.out.println("Position en x de la tour : " + maTour.getX());

        // test de la méthode getY()
        System.out.println("Position en y de la tour : " + maTour.getY());

        // test de la méthode move()
        maTour.move(2, 0);
        System.out.println("Position en x de la tour après déplacement : " + maTour.getX());
        System.out.println("Position en y de la tour après déplacement : " + maTour.getY());

        // test de la méthode isMoveOk()
        System.out.println("Déplacement en (2, 1) autorisé ? " + maTour.isMoveOk(2, 1));
        System.out.println("Déplacement en (2, 0) autorisé ? " + maTour.isMoveOk(2, 0));
        
        // Création d'un roi blanc en position (4, 4)
        Pieces monRoi = new Roi(Couleur.BLANC, new Coord(4, 4));
        // test de la méthode ToString()
        System.out.println(monRoi);

        // Test des déplacements légaux
        System.out.println("Déplacements légaux :");
        System.out.println(monRoi.isMoveOk(3, 4)); // vers la gauche
        System.out.println(monRoi.isMoveOk(5, 4)); // vers la droite
        System.out.println(monRoi.isMoveOk(4, 3)); // vers le bas
        System.out.println(monRoi.isMoveOk(4, 5)); // vers le haut
        System.out.println(monRoi.isMoveOk(3, 3)); // en diagonale vers le bas à gauche
        System.out.println(monRoi.isMoveOk(5, 5)); // en diagonale vers le haut à droite

        // Test des déplacements illégaux
        System.out.println("Déplacements illégaux :");
        System.out.println(monRoi.isMoveOk(2, 4)); // trop loin à gauche
        System.out.println(monRoi.isMoveOk(6, 4)); // trop loin à droite
        System.out.println(monRoi.isMoveOk(4, 2)); // trop loin en bas
        System.out.println(monRoi.isMoveOk(4, 6)); // trop loin en haut
        System.out.println(monRoi.isMoveOk(2, 2)); // trop loin en diagonale vers le bas à gauche
        System.out.println(monRoi.isMoveOk(6, 6)); // trop loin en diagonale vers le haut à droite
        
        // Création d'une reine noire en position (5, 5)
        Pieces maReine = new Reine(Couleur.NOIR, new Coord(5, 5));
        
        // test de la méthode ToString()
        System.out.println(maReine);
        
        // Test des déplacements légaux
        ;
        System.out.println(maReine.isMoveOk(5, 7)); // vers le haut
        System.out.println(maReine.isMoveOk(5, 3)); // vers le bas
        System.out.println(maReine.isMoveOk(7, 5)); // vers la droite
        System.out.println(maReine.isMoveOk(3, 5)); // vers la gauche
        System.out.println(maReine.isMoveOk(7, 7)); // en diagonale vers le haut à droite
        System.out.println(maReine.isMoveOk(3, 3)); // en diagonale vers le bas à gauche
        System.out.println(maReine.isMoveOk(7, 3)); // en diagonale vers le bas à droite
        System.out.println(maReine.isMoveOk(3, 7)); // en diagonale vers le haut à gauche

        // Test des déplacements illégaux
        System.out.println("Déplacements illégaux :");
        System.out.println(maReine.isMoveOk(8, 5)); // déplacement en dehors de la grille à droite
        System.out.println(maReine.isMoveOk(5, 8)); // déplacement en dehors de la grille en haut
        System.out.println(maReine.isMoveOk(-1, 5)); // déplacement en dehors de la grille à gauche
        System.out.println(maReine.isMoveOk(5, -1)); // déplacement en dehors de la grille en bas
        System.out.println(maReine.isMoveOk(3, 6)); // déplacement en L
        
        
        // Création d'un fou blanc en position (0, 0)
        Pieces monFou = new Fou(Couleur.BLANC, new Coord(0, 0));

        // Test de la méthode toString()
        System.out.println(monFou);

        // Test des déplacements légaux
        System.out.println("Déplacements légaux :");
        System.out.println(monFou.isMoveOk(3, 3)); // déplacement en diagonale vers le bas à droite
        System.out.println(monFou.isMoveOk(7, 7)); // déplacement en diagonale vers le haut à droite
        System.out.println(monFou.isMoveOk(1, 1)); // déplacement en diagonale vers le bas à gauche

        // Test des déplacements illégaux
        System.out.println("Déplacements illégaux :");
        System.out.println(monFou.isMoveOk(3, 0)); // déplacement horizontal
        System.out.println(monFou.isMoveOk(0, 3)); // déplacement vertical
        System.out.println(monFou.isMoveOk(2, 1)); // déplacement en L
        
        
        // Création d'un cavalier blanc en position (3, 3)
        Pieces monCavalier = new Cavalier(Couleur.BLANC, new Coord(3, 3));

        // Test de la méthode toString()
        System.out.println(monCavalier);

        // Test des déplacements légaux
        System.out.println("Déplacements légaux :");
        System.out.println(monCavalier.isMoveOk(5, 4)); 
        System.out.println(monCavalier.isMoveOk(1, 2)); 

        // Test des déplacements illégaux
        System.out.println("Déplacements illégaux :");
        System.out.println(monCavalier.isMoveOk(5, 3)); // déplacement horizontal
        System.out.println(monCavalier.isMoveOk(3, 4)); // déplacement vertical
        
        // Création d'un pion blanc en position (5, 1)
        Pieces monPion = new Pion(Couleur.BLANC, new Coord(5, 1));

        // Test de la méthode toString()
        System.out.println(monPion);

        // Test des déplacements légaux
        System.out.println("Déplacements légaux :");
        System.out.println(monPion.isMoveOk(5, 2)); // déplacement vertical d'une case
        System.out.println(monPion.isMoveOk(5, 3)); // déplacement vertical de deux cases (premier coup)

        // Test des déplacements illégaux
        System.out.println("Déplacements illégaux :");
        System.out.println(monPion.isMoveOk(4, 2)); // déplacement diagonal d'une case (case vide)
        System.out.println(monPion.isMoveOk(5, 4)); // déplacement vertical de trois cases

        // Test de la méthode move() pour un déplacement légal
        if (monPion.move(5, 3)) {
            System.out.println("Déplacement effectué : " + monPion);
        } else {
            System.out.println("Déplacement impossible");
        }



        
    }
}


