package model;

public class Cavalier extends AbstractPiece {

    public Cavalier(Couleur couleur_de_piece, Coord coord) {
        super(couleur_de_piece, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        // Vérifie si les coordonnées finales sont en dehors de la grille
        if (xFinal < 0 || xFinal > 7 || yFinal < 0 || yFinal > 7) {
            return false;
        }

        // Vérifie si le déplacement est en L
        int deltaX = Math.abs(getX() - xFinal);
        int deltaY = Math.abs(getY() - yFinal);
        if ((deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1)) {
            return true;
        }
        return false;
    }
}
