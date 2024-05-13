package model;

import java.util.ArrayList;
import java.util.List;

public class Echiquier implements BoardGames {

    private Jeu jeuBlanc;
    private Jeu jeuNoir;
    private String message;
    private Couleur joueurCourant;

    public Echiquier() {
        this.jeuBlanc = new Jeu(Couleur.BLANC);
        this.jeuNoir = new Jeu(Couleur.NOIR);
        this.message = "";
        this.joueurCourant = Couleur.BLANC; // Début de partie, le joueur blanc commence
    }

    public Couleur getColorCurrentPlayer() {
        return this.joueurCourant;
    }

    public String getMessage() {
        return this.message;
    }

    public Couleur getPieceColor(int x, int y) {
        Couleur pieceColor = jeuBlanc.getPieceColor(x, y);
        if (pieceColor != null) {
            return pieceColor;
        } else {
            return jeuNoir.getPieceColor(x, y);
        }
    }

    public List<PieceIHM> getPiecesIHM() {
        List<PieceIHM> piecesIHM = new ArrayList<>();

        // Parcours des pièces du jeu blanc
        for (PieceIHM pieceIHM : jeuBlanc.getPiecesIHM()) {
            piecesIHM.add(pieceIHM);
        }

        // Parcours des pièces du jeu noir
        for (PieceIHM pieceIHM : jeuNoir.getPiecesIHM()) {
            piecesIHM.add(pieceIHM);
        }

        return piecesIHM;
    }

    public boolean isEnd() {
        boolean kingWhiteFound = false;
        boolean kingBlackFound = false;

        // Parcours des pièces du jeu blanc pour trouver le roi blanc
        for (PieceIHM pieceIHM : getPiecesIHM()) {
            if (pieceIHM.getTypePiece().equals("Roi") && pieceIHM.getCouleur() == Couleur.BLANC) {
                kingWhiteFound = true;
                break;
            }
        }

        // Parcours des pièces du jeu noir pour trouver le roi noir
        for (PieceIHM pieceIHM : getPiecesIHM()) {
            if (pieceIHM.getTypePiece().equals("Roi") && pieceIHM.getCouleur() == Couleur.NOIR) {
                kingBlackFound = true;
                break;
            }
        }

        // Le jeu est terminé si l'un des rois n'est pas trouvé
        return !(kingWhiteFound && kingBlackFound);
    }

    /**
     * Vérifie si une pièce du jeu peut être déplacée aux coordonnées finales.
     *
     * @param xInit   Coordonnée x de départ de la pièce.
     * @param yInit   Coordonnée y de départ de la pièce.
     * @param xFinal  Coordonnée x finale où la pièce doit être déplacée.
     * @param yFinal  Coordonnée y finale où la pièce doit être déplacée.
     * @return        true si la pièce du jeu peut être déplacée aux coordonnées finales, false sinon.
     */
    public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
        // Récupérer la liste des pièces du jeu
        List<PieceIHM> piecesIHM = getPiecesIHM();

        // Rechercher la pièce à déplacer
        PieceIHM pieceToMove = null;
        for (PieceIHM piece : piecesIHM) {

            if (piece.getList().contains(new Coord(xInit, yInit)) && piece.getCouleur() == joueurCourant) {
                pieceToMove = piece;
                break;
            }
        }

        // Vérifier que la pièce à déplacer a été trouvée
        if (pieceToMove == null) {
            return false;
        }

        // Vérifier que les coordonnées finales sont valides
        if (xFinal < 0 || xFinal >= 8 || yFinal < 0 || yFinal >= 8) {
            return false;
        }

        // Vérifier que la case finale est vide ou occupee par une pièce adverse
        PieceIHM pieceFinal = null;
        for (PieceIHM piece : piecesIHM) {
            if (piece.getList().contains(new Coord(xFinal, yFinal))) {
                pieceFinal = piece;
                break;
            }
        }
        if (pieceFinal != null && pieceFinal.getCouleur() == joueurCourant) {
            return false;
        }

        // Vérifier que le déplacement est autorisé pour la pièce
        if (joueurCourant == Couleur.BLANC) {
            return jeuBlanc.isMoveOk(xInit, yInit, xFinal, yFinal);
        } else {
            return jeuNoir.isMoveOk(xInit, yInit, xFinal, yFinal);
        }
        

        
    }



    public void switchJoueur() {
        if (joueurCourant == Couleur.BLANC) {
            joueurCourant = Couleur.NOIR;
        } else {
            joueurCourant = Couleur.BLANC;
        }
    }

    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
        // Vérification de la validité du déplacement
        if (!isMoveOk(xInit, yInit, xFinal, yFinal)) {
            setMessage("Déplacement non autorisé");
            return false; // Déplacement non autorisé
        }

        // Déplacement de la pièce
        if (joueurCourant == Couleur.BLANC) {
            jeuBlanc.move(xInit, yInit, xFinal, yFinal);
        } else {
            jeuNoir.move(xInit, yInit, xFinal, yFinal);
        }

        // Passage au joueur suivant
        switchJoueur();

        setMessage("Déplacement OK");
        return true;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Échiquier : \n");
        builder.append("Jeu Blanc :\n").append(jeuBlanc.toString()).append("\n");
        builder.append("Jeu Noir :\n").append(jeuNoir.toString()).append("\n");
        builder.append("Message : ").append(message);
        return builder.toString();
    }

    public static void main(String[] args) {
        Echiquier echiquier = new Echiquier();
        System.out.println(echiquier);

        List<PieceIHM> pieces = echiquier.getPiecesIHM();
        System.out.println(pieces);

        // Test de déplacement
        echiquier.move(1, 6, 1, 5);
        System.out.println(echiquier);
    }
}
