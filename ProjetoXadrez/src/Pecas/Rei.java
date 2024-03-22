package Pecas;

import Jogo.Interface;
import Jogo.Quadrado;
import Jogo.Vazio;
import Jogo.Jogador;

public class Rei extends Peca {
    private boolean pMovReiB = false;
    private boolean pMovReiP = false;
    
    
    public Rei(String colorIn) {
	super(colorIn, "king");
		
	if(this.color.equals("white") == true){
	    symbol = " RB ";
	}
	else{
	    symbol = " RP";
	}
    }
        
    
    public boolean isNotfirstMove(boolean checkMove) {
	while(checkMove==false) {
	    return false;
	}
        
	return true;
    }
        
    
    public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {	      
	int moveFromX = moveFromReq[0];
	int moveFromY = moveFromReq[1];
	int moveToX = moveToReq[0];
	int moveToY = moveToReq[1];
		
	Quadrado toSquare = Interface.tabuleiro[moveToY][moveToX];
		
	for (int moveAwayX = -1; moveAwayX <= 1; moveAwayX++){
	    for (int moveAwayY = -1; moveAwayY <= 1; moveAwayY++){
		if(moveToX == moveFromX + moveAwayX && moveToY == moveFromY + moveAwayY){
		    if((toSquare.getType().equals("blank") == false) && (toSquare.getColor().equals(plyColor) == false)){
                        if(Interface.tabuleiro[moveFromY][moveFromX].getColor().equals("white") == true){
                            pMovReiB = true;
                        }
                        else if(Interface.tabuleiro[moveFromY][moveFromX].getColor().equals("black") == true){
                            pMovReiP = true;
                        }
                        
                        return true;
		    }
		    else if(toSquare.getType().equals("blank") == true){
                        if(Interface.tabuleiro[moveFromY][moveFromX].getColor().equals("white") == true){
                            pMovReiB = true;
                        }
                        else if (Interface.tabuleiro[moveFromY][moveFromX].getColor().equals("black") == true){
                            pMovReiP = true;
                        }
                        
                        return true;
		    }
		}
	    }
	}
        
        
        //Roque Pequeno Branco
        if(Interface.tabuleiro[7][6].getType().equals("blank") == true && Interface.tabuleiro[7][6].getColor().equals(color) == false && pMovReiB == false
                && Interface.tabuleiro[7][4].getColor().equals("white") == true && Interface.tabuleiro[7][5].getType().equals("blank") == true && Jogador.ispMovTorreBD() == false){
            
            Interface.tabuleiro[7][5] = new Torre("white");
            Interface.tabuleiro[7][7] = new Vazio();
            
            return true;
        }
        
        //Roque Grande Branco
        else if(Interface.tabuleiro[7][2].getType().equals("blank") == true && Interface.tabuleiro[7][2].getColor().equals(color) == false && pMovReiB == false
                && Interface.tabuleiro[7][4].getColor().equals("white") == true && Interface.tabuleiro[7][3].getType().equals("blank") == true 
                && Jogador.ispMovTorreBE() == false && Interface.tabuleiro[7][1].getType().equals("blank") == true){
            
            Interface.tabuleiro[7][3] = new Torre("white");
            Interface.tabuleiro[7][0] = new Vazio();
            
            return true;
        }
        
        //Roque Pequeno Preto
        else if(Interface.tabuleiro[0][6].getType().equals("blank") == true && Interface.tabuleiro[0][6].getColor().equals(color) == false && pMovReiP == false
                && Interface.tabuleiro[0][4].getColor().equals("black") == true && Interface.tabuleiro[0][5].getType().equals("blank") == true && Jogador.ispMovTorrePD() == false){
            
            Interface.tabuleiro[0][5] = new Torre("black");
            Interface.tabuleiro[0][7] = new Vazio();
            
            return true; 
        }
	
        //Roque Grande Preto
        else if(Interface.tabuleiro[0][2].getType().equals("blank") == true && Interface.tabuleiro[0][2].getColor().equals(color) == false && pMovReiP == false
                && Interface.tabuleiro[0][4].getColor().equals("black") == true && Interface.tabuleiro[0][3].getType().equals("blank") == true 
                && Jogador.ispMovTorrePE() == false && Interface.tabuleiro[0][1].getType().equals("blank") == true){
            
            Interface.tabuleiro[0][3] = new Torre("black");
            Interface.tabuleiro[0][0] = new Vazio();
            
            return true;
        }
        
        
	return false;
    }
    
}