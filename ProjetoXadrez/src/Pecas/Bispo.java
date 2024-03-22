package Pecas;


import Jogo.Interface;
import Jogo.Quadrado;

public class Bispo extends Peca {
   public Bispo(String colorIn) {
	super(colorIn, "bishop");
		
	if(color.equals("white")){
	    symbol = " BB";
	}
	else{
	    symbol = " BP";
	}
    }

    public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
	int moveFromX = moveFromReq[0];
	int moveFromY = moveFromReq[1];
	int moveToX = moveToReq[0];
	int moveToY = moveToReq[1];
		
	Quadrado toSquare = Interface.tabuleiro[moveToY][moveToX];
		
	int moveDistance = Math.abs(moveToX - moveFromX);
		
	if(!testKing){
	    if(toSquare.getType().equals("king")){
		return false;
	    }
	}
		
		String direction; 
		
		if(moveToX > moveFromX){
			if(moveToY < moveFromY){
				direction = "topRite";
			}
			else{
				direction = "botRite";
			}
		}
		else{
			if(moveToY < moveFromY){
				direction = "topLeft";
			}
			else{
				direction = "botLeft";
			}
		}
		
		
		Quadrado testSquare; 
		
		
		for(int diagMoveAway = 1; diagMoveAway <= moveDistance; diagMoveAway++){
			
			if(direction == "topRite"){
				testSquare = Interface.tabuleiro[moveFromY - diagMoveAway][moveFromX + diagMoveAway];
			}
			else if(direction == "botRite"){
				testSquare = Interface.tabuleiro[moveFromY + diagMoveAway][moveFromX + diagMoveAway];
			}
			else if(direction == "topLeft"){
				testSquare = Interface.tabuleiro[moveFromY - diagMoveAway][moveFromX - diagMoveAway];
			}
			else{ 
				testSquare = Interface.tabuleiro[moveFromY + diagMoveAway][moveFromX - diagMoveAway];
			}
			
			if((testSquare.getType() != "blank") && (diagMoveAway != moveDistance)){
				return false;
			}
			else if((diagMoveAway == moveDistance) && ((testSquare.getColor() != plyColor) || (testSquare.getType() == "blank"))){
				return true;
			}
		}
		return false; 
	}
}