package Jogo;

public class Vazio extends Quadrado{
    
    public Vazio() {
	super("blank");
	symbol = "      ";
	color = ""; 	
    }

    public void move(int[] moveToLoc) {
    }

    public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) { 
	return false;
    }

}
