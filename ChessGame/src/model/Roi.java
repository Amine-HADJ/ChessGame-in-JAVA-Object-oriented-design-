package model;

public class Roi extends AbstractPiece {

    public Roi(Couleur couleur_de_piece, Coord coord) {
        super(couleur_de_piece, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        // Vérifier si le déplacement est légal pour le roi
        int deltaX = Math.abs(xFinal - getX());
        int deltaY = Math.abs(yFinal - getY());

        // Le roi peut se déplacer d'une case dans toutes les directions
        if (deltaX <= 1 && deltaY <= 1) {
            return true;
        }

        return false;
    }
}
