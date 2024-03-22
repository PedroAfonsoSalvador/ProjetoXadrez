package Pecas;

import Jogo.Interface;
import Jogo.Quadrado;

public class Dama extends Peca {
	
	public Dama(String colorIn) {
		super(colorIn, "queen");
		
		if(color == "white"){
			symbol = " DB";
		}
		else{
			symbol = " DP";
		}
	}

	@Override
	public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
		
		int moveFromX = moveFromReq[0];
		int moveFromY = moveFromReq[1];
		int moveToX = moveToReq[0];
		int moveToY = moveToReq[1];
		
		Quadrado toSquare = Interface.tabuleiro[moveToY][moveToX];
		
		String direction;
		String type; 
		
		if(!testKing){
			if(toSquare.getType() == "king"){
				return false; 
			}
		}
		
		if(moveToY == moveFromY){ 
			if(moveToX > moveFromX){
				direction = "rite";
				type = "straight";
			}
			else{
				direction = "left";
				type = "straight";
			}
		}
		
		else if(moveToX == moveFromX){
			if(moveToY > moveFromY){
				direction = "bot";
				type = "straight";
			}
			else{
				direction = "top";
				type = "straight";
			}
		}
		else if(moveToX > moveFromX){
			if(moveToY < moveFromY){
				direction = "topRite";
				type = "diagonal";
			}
			else{
				direction = "botRite";
				type = "diagonal";
			}
		}
		else if(moveToX < moveFromX){
			if(moveToY < moveFromY){
				direction = "topLeft";
				type = "diagonal";
			}
			else{
				direction = "botLeft";
				type = "diagonal";
			}
		}
		else{
			return false;
		}
		
		Quadrado testSquare;
		
		if(type == "diagonal"){
			int moveDistance = Math.abs(moveToX - moveFromX);
		
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
				else{ //botLeft
					testSquare = Interface.tabuleiro[moveFromY + diagMoveAway][moveFromX - diagMoveAway];
				}
			
				if((testSquare.getType() != "blank") && (diagMoveAway != moveDistance)){
					return false;
				}
				else if((diagMoveAway == moveDistance) && ((testSquare.getColor() != plyColor) || (testSquare.getType() == "blank"))){
					return true;
				}
			}
		}
		else{ 
			if((direction == "rite") || (direction == "left")){
				int displaceMax = Math.abs(moveToX - moveFromX); 
		
				for(int displace = 1; displace <= displaceMax; displace++){ 
					if(direction == "rite"){
						testSquare = Interface.tabuleiro[moveFromY][moveFromX + displace];
					
						if((testSquare.getType() != "blank") && (displace != displaceMax)){
						return false;
						}
						else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getColor() != plyColor))){
							return true;
						}
					}
					else{
						testSquare = Interface.tabuleiro[moveFromY][moveFromX - displace];
					
						if((testSquare.getType() != "blank") && (displace != displaceMax)){
							return false;
						}
						else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getColor() != plyColor))){
							return true;
						}
					}
				}
			}
			else{
				int displaceMax = Math.abs(moveToY - moveFromY); 
				
				for(int displace = 1; displace <= displaceMax; displace++){ 
				
					if(direction == "top"){
						testSquare = Interface.tabuleiro[moveFromY - displace][moveFromX];
					
						if((testSquare.getType() != "blank") && (displace != displaceMax)){
							return false;
						}
						else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getColor() != plyColor))){
							return true;
						}
					}
					else{
						testSquare = Interface.tabuleiro[moveFromY + displace][moveFromX];
					
						if((testSquare.getType() != "blank") && (displace != displaceMax)){
							return false;
						}
						else if((displace == displaceMax) && ((testSquare.getType() == "blank") || (testSquare.getColor() != plyColor))){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
/*
public class Dama extends Peca {	
    public Dama(String colorIn) {
	super(colorIn, "queen");
		
	if(color.equals("white") == true){
	    symbol = " DB";
	}
	else{
	    symbol = " DP";
	}
    }

    
    @Override
    public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {	
	int moveFromX = moveFromReq[0];
	int moveFromY = moveFromReq[1];
	int moveToX = moveToReq[0];
	int moveToY = moveToReq[1];
        Quadrado testSquare;
	String direction;
	String type;	
        
	Quadrado toSquare = Interface.tabuleiro[moveToY][moveToX];
		
	if(!testKing){
	    if(toSquare.getType().equals("king") == true){
		return false; 
	    }
	}
		
	if(moveToY == moveFromY){ 
	    if(moveToX > moveFromX){
		direction = "rite";
		type = "straight";
	    }
	    else{
		direction = "left";
		type = "straight";
	    }
	}
		
	else if(moveToX == moveFromX){
	    if(moveToY > moveFromY){
		direction = "bot";
		type = "straight";
	    }
	    else{
		direction = "top";
		type = "straight";
	    }
	}
        
	else if(moveToX > moveFromX){
	    if(moveToY < moveFromY){
		direction = "topRite";
		type = "diagonal";
	    }
	    else{
		direction = "botRite";
		type = "diagonal";
	    }
	}
        
	else if(moveToX < moveFromX){
	    if(moveToY < moveFromY){
		direction = "topLeft";
		type = "diagonal";
	    }
	    else{
		direction = "botLeft";
		type = "diagonal";
	    }
	}
        
	else{
	    return false;
	}
		
		
	if(type.equals("diagonal") == true){
	    int moveDistance = Math.abs(moveToX - moveFromX);
		
	    for(int diagMoveAway = 1; diagMoveAway <= moveDistance; diagMoveAway++){
		if(direction.equals("topRite") == true){
		    testSquare = Interface.tabuleiro[moveFromY - diagMoveAway][moveFromX + diagMoveAway];
		}
		else if(direction.equals("botRite") == true){
		    testSquare = Interface.tabuleiro[moveFromY + diagMoveAway][moveFromX + diagMoveAway];
		}
		else if(direction.equals("topLeft") == true){
		    testSquare = Interface.tabuleiro[moveFromY - diagMoveAway][moveFromX - diagMoveAway];
		}
		else{ //botLeft
		    testSquare = Interface.tabuleiro[moveFromY + diagMoveAway][moveFromX - diagMoveAway];
		}
			
                
		if((testSquare.getType().equals("blank") == false) && (diagMoveAway != moveDistance)){
		    return false;
		}
		else if((diagMoveAway == moveDistance) && ((testSquare.getColor().equals(plyColor) == false) || (testSquare.getType().equals("blank") == true))){
		    return true;
		}
	    }
	}
        
        
	else{ 
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
	}
        
	return false;
    }
}
*/