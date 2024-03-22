package Jogo;

import Exceptions.InvalidMoveException;
import javax.swing.JOptionPane;

public class Jogador {
    public static String nameB, nameP;
    public String color;
    private static boolean pMovTorreBD = false, pMovTorreBE = false;
    private static boolean pMovTorrePD = false, pMovTorrePE = false;
    private static boolean serPromovido = false;

    
    public Jogador(String nameIn, String colorIn){
        color = colorIn;
        
        if(color.equals("white") == true){
            nameB = nameIn;
        }
        else{
            nameP = nameIn;
        }
    }
	
    
    
    private int convertCharToNum(char charIn){ //if this method returns a -1 the letter is not valid
	//the user will enter a char but it need to be a num for program
	int numOut = -1;
		
	for(int i = 0; i < Interface.SIDE_LETTERS.length; i++){
	    if(Tabuleiro.SIDE_LETTERS[i] == charIn){
		numOut = i;
	    }
	}
        
	return numOut;
    }
	
    
    
    private int convertCharNumtoNum(char charIn){ //convert string number to real int and check that it is less than 8
	int numOut = -1; //if -1 is returned the number is not valid
	int convertedNum = Character.getNumericValue(charIn); 
		
	for(int i: Interface.SIDE_NUMS){
	    if(i == convertedNum){
		numOut = convertedNum;	//-1 so that the locations are stored starting from 0 	  
	    }
	}
        
	return numOut;
    }
	
    
    
    public int[][] getMove(){ //returns array of x and y for the spot to move from and to
	int[][] move = new int[2][2];   
        String moveIn = "";  
        int xLocal = -1, yLocal = -1; 
 
        
	for(int runNum = 1; runNum <= 2; runNum++){
	    while(true){  
                try{
		    if(runNum == 1){ //on first run ask for location to move from
                        moveIn = JOptionPane.showInputDialog(null, "Digite as coordenadas da peça que deseja mover: ");
		    }  
	            else{ //on second run ask pos to move to
                        moveIn = JOptionPane.showInputDialog(null, "Digite as coordenadas para onde a peça deve ir: ");
		    }
                
		    //checking for blank spaces and nothing entered and no more than 2 characters long
		    if(!moveIn.isEmpty() && moveIn.length() <= 2 && !(moveIn.contains(" ") || moveIn.contains("\t"))){		
		        if(!Character.isDigit(moveIn.charAt(0)) && Character.isDigit(moveIn.charAt(1))){
			    //making sure first char is a char and making sure the second is a digit
			    int x = 0, y = 0;
                            
			    if((x = convertCharToNum(Character.toUpperCase(moveIn.charAt(0)))) != -1){
			        if((y = convertCharNumtoNum(moveIn.charAt(1))) != -1){
				    y = 8 - y; //flipping value so that 0 index is on top
				    int tempArray[] = {x, y};
                                    
				    if(runNum == 1){ 
				        if(Interface.tabuleiro[y][x].getType().equals("blank") == true || Interface.tabuleiro[y][x].getColor().equals(color) == false){ 
                                            //returning an array full of -1's if the first location does not point to a piece
					    //or if the piece is not of the same color as the player
					    tempArray[0] = -1;																
					    tempArray[1] = -1;
					    int[][] errorArray = {tempArray, tempArray};
                                        
					    return errorArray;
				        }
                                        else{
                                            xLocal = x;
                                            yLocal = y;
                                        }  
				    }
                                    
                                    else{           
                                        //Promoção do Peão
                                        if(Interface.tabuleiro[yLocal][xLocal].getType().equals("pawn") == true && (y == 0 || y == 8)){
                                            if(Interface.tabuleiro[yLocal][xLocal].getColor().equals("white") == true){
                                                serPromovido = true;
                                            }
                                            else{
                                                serPromovido = true;    
                                            }
                                        }
                                        else{
                                            serPromovido = false; 
                                        }
                                        
                                        
                                         //Primeiro Movimento das Torres
                                        if(yLocal == 7 && xLocal == 7){ //Torre Direita Branca
                                           if(Interface.tabuleiro[yLocal][xLocal].getSymbol().equals(" TB") == true){
                                                pMovTorreBD = true;
                                            }
                                        }
                                        else if(yLocal == 7 && xLocal == 0){ //Torre Esquerda Preta
                                            if(Interface.tabuleiro[yLocal][xLocal].getSymbol().equals(" TB") == true){
                                                pMovTorreBE = true;
                                            }
                                        }
                                        else if(yLocal == 0 && xLocal == 7){ //Torre Direita Preta
                                            if(Interface.tabuleiro[yLocal][xLocal].getSymbol().equals(" TP") == true){
                                                pMovTorrePD = true;
                                            }
                                        }
                                        else if(yLocal == 0 && xLocal == 0){ //Torre Direita Preta
                                            if(Interface.tabuleiro[yLocal][xLocal].getSymbol().equals(" TP") == true){
                                                pMovTorrePE = true;
                                            }
                                        }
                                    }
                                
				    move[runNum - 1] = tempArray;                                
				    break;
			        }
			    }
		        }
		    }
                          
                    throw new InvalidMoveException();
                }catch(InvalidMoveException e){
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                
	    }		
	} 
        
	return move;
    }
        
    
    
    public static String getNomeBranco(){
        return nameB;
    }
    
    public static String getNomePreto(){
        return nameP;
    }

    
    
    public static boolean ispMovTorreBD() {
        return pMovTorreBD;
    }

    public static boolean ispMovTorreBE() {
        return pMovTorreBE;
    }

    public static boolean ispMovTorrePD() {
        return pMovTorrePD;
    }

    public static boolean ispMovTorrePE() {
        return pMovTorrePE;
    }
    
    
    
    
    public static boolean isPromovido(){
        return serPromovido;
    }

}
