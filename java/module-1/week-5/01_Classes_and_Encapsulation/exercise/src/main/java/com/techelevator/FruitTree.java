package com.techelevator;

public class FruitTree {

    private String typeOfFruit;
    private int piecesOfFruitLeft;

    public String getTypeOfFruit() {
        return typeOfFruit;
    }

    public int getPiecesOfFruitLeft() {
        return piecesOfFruitLeft;
    }

    public FruitTree() {
    }

    public FruitTree(String typeOfFruit, int piecesOfFruitLeft) {
        this.typeOfFruit = typeOfFruit;
        this.piecesOfFruitLeft = piecesOfFruitLeft;
    }

    public boolean pickFruit(int numberOfPiecesToRemove){
        if (piecesOfFruitLeft < numberOfPiecesToRemove)
            //need to pick more than what's left
            return false;
        else
            piecesOfFruitLeft -= numberOfPiecesToRemove;
        return true;
    }
}
