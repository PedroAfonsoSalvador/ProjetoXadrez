package Jogo;

public abstract class Quadrado {	
    protected String symbol;
    public String color; 
    public String type;
		
    
    public Quadrado(String typeIn){
	type = typeIn;
    }
		
    public String getSymbol(){
	return symbol;
    }
    
    public String getColor(){
	return color;
    }
    
    public String getType(){
	return type;
    }
    
    public void SetType(String type) {
	type=this.type;
    }
		
    
    public abstract boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing);
}