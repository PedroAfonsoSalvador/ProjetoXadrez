package Pecas;

import Jogo.Interface;
import Jogo.Quadrado;

public class Torre extends Peca {
    public Torre(String colorIn) {
	super(colorIn, "rook");
		
	if(color.equals("white") == true){
	    symbol = " TB";
	}
	else{
	    symbol = " TP";
	}
    }

    
    public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {	
	int moveFromX = moveFromReq[0];
	int moveFromY = moveFromReq[1];
	int moveToX = moveToReq[0];
	int moveToY = moveToReq[1];
        Quadrado testSquare;
        String direction;
	boolean pMovTorreBD = false, pMovTorreBE = false;
        boolean pMovTorrePD = false, pMovTorrePE = false;
        
	Quadrado toSquare = Interface.tabuleiro[moveToY][moveToX];
		
        
	if(!testKing){
	    if(toSquare.getType().equals("king") == true){
		return false; 
	    }
	}
		
        
	if(moveToY == moveFromY){
	    if(moveToX > moveFromX){
		direction = "rite";
	    }
	    else{
		direction = "left";
	    }
	}
		
	else if(moveToX == moveFromX){
	    if(moveToY > moveFromY){
		direction = "bot";
	    }
	    else{
		direction = "top";
	    }
	}
        
	else{
	    return false;
	}
		
        
        
	if((direction.equals("rite") == true) || (direction.equals("left") == true)){
	    int displaceMax = Math.abs(moveToX - moveFromX); 
		
	    for(int displace = 1; displace <= displaceMax; displace++){ 
		if(direction.equals("rite") == true){
		    testSquare = Interface.tabuleiro[moveFromY][moveFromX + displace];
					
		    if((testSquare.getType().equals("blank") == false) && (displace != displaceMax)){
			return false;
		    }
		    else if((displace == displaceMax) && ((testSquare.getType().equals("blank") == true) || (testSquare.getColor().equals(plyColor) == false))){
			return true;
		    }
		}
                
		else{
		    testSquare = Interface.tabuleiro[moveFromY][moveFromX - displace];
					
		    if((testSquare.getType().equals("blank") == false) && (displace != displaceMax)){
			return false;
		    }
		    else if((displace == displaceMax) && ((testSquare.getType().equals("blank") == true) || (testSquare.getColor().equals(plyColor) == false))){
			return true;
		    }
		}
	    }
	}
        
	else{ 
	    int displaceMax = Math.abs(moveToY - moveFromY); 
				
	    for(int displace = 1; displace <= displaceMax; displace++){ 		
		if(direction.equals("top") == true){
		    testSquare = Interface.tabuleiro[moveFromY - displace][moveFromX];
					
		    if((testSquare.getType().equals("blank") == false) && (displace != displaceMax)){
			return false;
		    }
		    else if((displace == displaceMax) && ((testSquare.getType().equals("blank") == true) || (testSquare.getColor().equals(plyColor) == false))){
			return true;
		    }
		}
                
		else{
		    testSquare = Interface.tabuleiro[moveFromY + displace][moveFromX];
					
		    if((testSquare.getType().equals("blank") == false) && (displace != displaceMax)){
			return false;
		    }
		    else if((displace == displaceMax) && ((testSquare.getType().equals("blank") == true) || (testSquare.getColor().equals(plyColor) == false))){        
                        return true;
		    }
		}
	    }
	}
        
	return false;
    }
}