package model;

public class Reine extends AbstractPiece {

    public Reine(Couleur couleur_de_piece, Coord coord) {
        super(couleur_de_piece, coord);
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        // Vérifie si les coordonnées finales sont en dehors de la grille
        if (xFinal < 0 || xFinal > 7 || yFinal < 0 || yFinal > 7) {
            return false;
        }

        // Vérifie si le déplacement est horizontal, vertical ou diagonal
        if (getX() == xFinal || getY() == yFinal || Math.abs(getX() - xFinal) == Math.abs(getY() - yFinal)) {
            return true;
        }
        return false;
    }


}
