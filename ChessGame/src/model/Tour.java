package model;

public class Tour extends AbstractPiece implements Pieces {

    public Tour(Couleur couleur_de_piece, Coord coord) {
        super(couleur_de_piece, coord);
    }
    
    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        // Vérifie si le déplacement est horizontal ou vertical
        if (getX() == xFinal || getY() == yFinal) {
            // Vérifie si la distance est valide
            int distance = Math.abs(xFinal - getX()) + Math.abs(yFinal - getY());
            if (distance > 0 && distance <= 7) {
                return true;
            }
        }
        return false;
    }
    
    

    
    
    
}
