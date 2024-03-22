package Pecas;


import Jogo.Interface;
import Jogo.Quadrado;

public class Peao extends Peca {
    public Peao(String colorIn) {
	super(colorIn, "pawn");
		
	if(color.equals("white") == true){
	    symbol = " PB";
	}
	else{
	    symbol = " PP";
	}
    }
	
    
    public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
	int moveFromX = moveFromReq[0];
	int moveFromY = moveFromReq[1];
	int moveToX = moveToReq[0];
	int moveToY = moveToReq[1];
		
	int moveForwardTwo; 
	int moveForwardOne;
	int pawnRowOnPlySide; 
		
	Quadrado toSquare = Interface.tabuleiro[moveToY][moveToX];
		
	if(!testKing){
	    if(toSquare.getType().equals("king") == true){
		return false; 
	    }
	}
		
        
	if(plyColor.equals("white") == true){ 
	    moveForwardTwo = -2;
	    moveForwardOne = -1;
	    pawnRowOnPlySide = 6;
	}
	else{ 
	    moveForwardTwo = 2;
	    moveForwardOne = 1;
	    pawnRowOnPlySide = 1;
	}
		
        
	if(moveToY == moveFromY + moveForwardOne){
	    if((moveToX == moveFromX - 1) || (moveToX == moveFromX + 1)){
		if((toSquare.getType().equals("blank") == false) && (toSquare.getColor().equals(plyColor) == false)){
		    return true; 
		}
	    }	
	    else if((moveToX == moveFromX) && (toSquare.getType().equals("blank") == true)){ 
		return true;
	    }
	}
        
	else if((moveToY == moveFromY + moveForwardTwo) && (moveToX == moveFromX) && (toSquare.getType().equals("blank") == true)){ 
	    if(moveFromY == pawnRowOnPlySide){ 
		return true;
	    }
	}
		
	return false; 
	}	
}