package model;


public interface Pieces {

    boolean capture();

    Couleur getCouleur();

    int getX();

    int getY();

    boolean isMoveOk(int xFinal, int yFinal);

    boolean move(int xFinal, int yFinal);

}
