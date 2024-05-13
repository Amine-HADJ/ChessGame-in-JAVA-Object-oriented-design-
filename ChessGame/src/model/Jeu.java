package model;

import java.util.LinkedList;
import java.util.List;
import tools.ChessPiecesFactory;

public class Jeu {

    private List<Pieces> pieces;
    


    public Jeu(Couleur couleur) {
        pieces = ChessPiecesFactory.newPieces(couleur);
    }
    
        
    
    private Pieces findPiece(int x, int y) {
        for (Pieces piece : pieces) {
            if (piece.getX() == x && piece.getY() == y) {
                return piece;
            }
          
        }
        return null; // Retourne null si aucune pièce n'est trouvée aux coordonnées spécifiées
    }
    
    public boolean isPieceHere(int x, int y) {
    	
    	boolean ret = false;
    	if (findPiece(x,y) != null) {
    		ret =true;
    	}
    	return ret;
    }
    
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
        boolean ret = false;

        // Vérifier si une pièce se trouve aux coordonnées initiales
        Pieces piece = findPiece(xInit, yInit);
        if (piece == null) {
            return false; // Aucune pièce aux coordonnées initiales
        }

        // Vérifier si le déplacement est valide pour la pièce en question
        if (piece.isMoveOk(xFinal, yFinal)) {
            // Vérifier si une pièce se trouve déjà aux coordonnées finales
            ret = !isPieceHere(xFinal, yFinal);
        }

        return ret;
    }
    
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        // Recherche de la pièce aux coordonnées initiales
        Pieces piece = findPiece(xInit, yInit);

        // Vérification de la validité du déplacement et exécution si possible
        boolean isValidMove = (piece != null) && isMoveOk(xInit, yInit, xFinal, yFinal);
        if (isValidMove) {
            piece.move(xFinal, yFinal);
        }

        return isValidMove;
    }
    
    public void setPossibleCapture() {
    	//boolean possibleCapture = false;
        // Parcourir toutes les pièces du jeu
        //for (Pieces piece : pieces) {
            // Vérifier si la pièce peut capturer une pièce adverse
          //  for (Pieces autrePiece : pieces) {
            //    if (piece.getCouleur() != autrePiece.getCouleur() && piece.isMoveOk(autrePiece.getX(), autrePiece.getY())) {
                    // Une capture est possible, mettre à jour et sortir de la méthode
             //       possibleCapture = true;
                    
            //    }
        //    }
      //  }

        // Si aucune capture n'a été trouvée, mettre à jour le booléen à false
     //   possibleCapture = false;
      //  return;
    }
    
    public Couleur getPieceColor(int x, int y) {
        Pieces piece = findPiece(x, y);
        if (piece != null) {
            return piece.getCouleur();
        } else {
            // Si aucune pièce n'est trouvée aux coordonnées spécifiées, retourner null
            return null;
        }
    }
    
    public String getPieceType(int x, int y) {
        Pieces piece = findPiece(x, y);
        if (piece != null) {
            return piece.getClass().getSimpleName();
        } else {
            // Si aucune pièce n'est trouvée aux coordonnées spécifiées, retourner une chaîne vide
            return "";
        }
    }
    
    
    public Couleur getCouleur() {
        if (!pieces.isEmpty()) {
            return pieces.get(0).getCouleur();
        } else {
            // Si la liste de pièces est vide, retourner une valeur par défaut (Couleur.BLANC)
            return Couleur.BLANC; 
        }
    }
    
    /**
    * @return une vue de la liste des pièces en cours
    * ne donnant que des accès en lecture sur des PieceIHM * (type piece + couleur + liste de coordonnées)
    */
    public List<PieceIHM> getPiecesIHM(){ 
    	PieceIHM newPieceIHM = null;
    	List<PieceIHM> list = new LinkedList<PieceIHM>();
    	
    	for (Pieces piece : pieces){
    		boolean existe = false;
		    // si le type de piece existe déjà dans la liste de PieceIHM
		    // ajout des coordonnées de la pièce dans la liste de Coord de ce type // si elle est toujours en jeu (x et y != -1)
    		for ( PieceIHM pieceIHM : list){
    			if ((pieceIHM.getTypePiece()).equals(piece.getClass().getSimpleName())){ 
    				existe = true;
    				if (piece.getX() != -1){
    					pieceIHM.add(new Coord(piece.getX(), piece.getY()));
    				} 
    			}
    		}
           // sinon, création d'une nouvelle PieceIHM si la pièce est toujours en jeu
    		if (! existe) {
    			if (piece.getX() != -1){
    				newPieceIHM = new PieceIHM(piece.getClass().getSimpleName(), piece.getCouleur());
    			    newPieceIHM.add(new Coord(piece.getX(), piece.getY())); list.add(newPieceIHM);
    			}
    		}
    	}
   
    	return list;
    }
    
    private boolean castlingEnabled = false;

    public void setCastling() {
        castlingEnabled = true;
    }
    
    public boolean isPawnPromotion(int xFinal, int yFinal) {
        // Vérifier si une pièce se trouve aux coordonnées finales
        Pieces piece = findPiece(xFinal, yFinal);
        // Si aucune pièce n'est présente ou que ce n'est pas un pion, retourner false
        if (piece == null || !(piece instanceof Pion)) {
            return false;
        }
        // Si c'est un pion, vérifier s'il peut être promu
        Pion pion = (Pion) piece;
        return (pion.getCouleur() == Couleur.BLANC && yFinal == 7) || (pion.getCouleur() == Couleur.NOIR && yFinal == 0);
    }
    
    
    public boolean pawnPromotion(int xFinal, int yFinal, String type) {
        // Vérifier si une pièce se trouve aux coordonnées finales et si c'est un pion
        Pieces piece = findPiece(xFinal, yFinal);
        if (piece == null || !(piece instanceof Pion)) {
            return false; // Retourner false si aucune pièce ou si ce n'est pas un pion
        }
        
        Pion pion = (Pion) piece;
        Couleur couleur = pion.getCouleur();
        
        // Si le pion peut être promu, créer une nouvelle pièce du type spécifié
        if (isPawnPromotion(xFinal, yFinal)) {
            switch (type.toUpperCase()) {
                case "TOUR":
                    pieces.add(new Tour(couleur, new Coord(xFinal, yFinal)));
                    break;
                case "CAVALIER":
                    pieces.add(new Cavalier(couleur, new Coord(xFinal, yFinal)));
                    break;
                case "FOU":
                    pieces.add(new Fou(couleur, new Coord(xFinal, yFinal)));
                    break;
                case "REINE":
                    pieces.add(new Reine(couleur, new Coord(xFinal, yFinal)));
                    break;
                default:
                    return false; // Retourner false si le type spécifié n'est pas valide
            }
            // Supprimer le pion promu de la liste des pièces
            pieces.remove(pion);
            return true; // Retourner true si la promotion a réussi
        }
        return false; // Retourner false si le pion ne peut pas être promu
    }
    
    
    
    public Coord getKingCoord() {
        for (Pieces piece : pieces) {
            if (piece instanceof Roi) {
                return new Coord(piece.getX(), piece.getY());
            }
        }
        return null; // Retourne null si aucun roi n'est trouvé
    }

    
    

    
    
    
    


    
    
    
    
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Pieces piece : pieces) {
            builder.append(piece.toString()).append("\n");
        }
        return builder.toString();
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
        
        boolean heree = jeuNoir.isPieceHere(x,3);
        System.out.println(heree);
        
        boolean moveeok = jeuNoir.isMoveOk(7, 1, 7, 2);
        System.out.println(moveeok);
        
        Coord coordooRoi = jeuNoir.getKingCoord();
        System.out.println("coord roi: " + coordooRoi);
        
        
        
        
        
    }
}