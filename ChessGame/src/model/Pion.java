package model;

public class Pion extends AbstractPiece implements Pions {

    private boolean premierCoup = true;

    public Pion(Couleur couleur_de_piece, Coord coord) {
        super(couleur_de_piece, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        // Vérifie si les coordonnées finales sont en dehors de la grille
        if (xFinal < 0 || xFinal > 7 || yFinal < 0 || yFinal > 7) {
            return false;
        }

        // Vérifie si le déplacement est vertical d'une ou deux cases
        if (getX() == xFinal && (Math.abs(getY() - yFinal) == 1 || (premierCoup && Math.abs(getY() - yFinal) == 2))) {
            return true;
        }

        // Vérifie si le déplacement est diagonal d'une case
        if (Math.abs(getX() - xFinal) == 1 && Math.abs(getY() - yFinal) == 1) {
            return isMoveDiagOk(xFinal, yFinal);
        }

        return false;
    }

    @Override
    public boolean isMoveDiagOk(int xFinal, int yFinal) {
       
        return false;
    }

    @Override
    public boolean move(int x, int y) {
        boolean result = super.move(x, y);
        if (result) {
            premierCoup = false;
        }
        return result;
    }
}
