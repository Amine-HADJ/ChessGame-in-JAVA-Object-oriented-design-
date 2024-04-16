package model;

import java.util.List;
import tools.ChessPiecesFactory;

public class Jeu {

    private List<Pieces> pieces;

    public Jeu(Couleur couleur) {
        pieces = ChessPiecesFactory.newPieces(couleur);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Pieces piece : pieces) {
            builder.append(piece.toString()).append("\n");
        }
        return builder.toString();
    }
    
    
    private Pieces findPiece(int x, int y) {
        for (Pieces piece : pieces) {
            if (piece.getX() == x && piece.getY() == y) {
                return piece;
            }
        }
        return null; // Retourne null si aucune pièce n'est trouvée aux coordonnées spécifiées
    }
   
    public static void main(String[] args) {
        // Testons la création d'un jeu avec des pièces blanches
        Jeu jeuBlanc = new Jeu(Couleur.BLANC);
        System.out.println("Pièces du jeu blanc :");
        System.out.println(jeuBlanc);

        // Testons la création d'un jeu avec des pièces noires
        Jeu jeuNoir = new Jeu(Couleur.NOIR);
        System.out.println("Pièces du jeu noir :");
        System.out.println(jeuNoir);
        
        int x = 6;
        int y = 1;
        Pieces pieceTrouvee = jeuNoir.findPiece(x, y);
        if (pieceTrouvee != null) {
            System.out.println("Pièce trouvée aux coordonnées (" + x + ", " + y + "): " + pieceTrouvee);
        } else {
            System.out.println("Aucune pièce trouvée aux coordonnées (" + x + ", " + y + ")");
        }
    }
}