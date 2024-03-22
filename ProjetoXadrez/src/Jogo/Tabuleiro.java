package Jogo;

import Exceptions.InvalidMoveException;
import java.util.Scanner;
import Pecas.*; 

public class Tabuleiro {

	static final char SIDE_LETTERS[] = {'A','B','C','D','E','F','G','H'};
	static final int SIDE_NUMS[] = {1,2,3,4,5,6,7,8};
	public static Quadrado tabuleiro[][] = new Quadrado[8][8];
	
	private static final Scanner scanner = new Scanner(System.in);
	
	private static void setup(){
	
		tabuleiro[0][0] = new Torre("black");
		tabuleiro[0][1] = new Cavalo("black");
		tabuleiro[0][2] = new Bispo("black");
		tabuleiro[0][3] = new Dama("black");
		tabuleiro[0][4] = new Rei("black");
		tabuleiro[0][5] = new Bispo("black");
		tabuleiro[0][6] = new Cavalo("black");
		tabuleiro[0][7] = new Torre("black");
		
		
		for(int i = 0; i < 8; i++){
			tabuleiro[1][i] = new Peao("black");
		}
		
		for(int i = 2; i < 6; i++){ 
			for(int j = 0; j < 8; j++){
				tabuleiro[i][j] = new Vazio();
			}
		}
		
		
		for(int i = 0; i < 8; i++){
			tabuleiro[6][i] = new Peao("white");
		}

		tabuleiro[7][0] = new Torre("white");
		tabuleiro[7][1] = new Cavalo("white");
		tabuleiro[7][2] = new Bispo("white");
		tabuleiro[7][3] = new Dama("white");
		tabuleiro[7][4] = new Rei("white");
		tabuleiro[7][5] = new Bispo("white");
		tabuleiro[7][6] = new Cavalo("white");
		tabuleiro[7][7] = new Torre("white");
		
	}
	
	private static String checkForCheckOrMate(String plyColor){ 
		/*for(int kingY = 0; kingY < 8; kingY++){
			for(int kingX = 0; kingX < 8; kingX++){
				Quadrado kingSquare = tabuleiro[kingY][kingX];
				
				String kingColor;
				if(plyColor == "white"){
					kingColor = "black";
				}
				else{ //black
					kingColor = "white";
				}
				
				if((kingSquare.getType() == "king") && (kingSquare.getColor() == kingColor)){
					
					for(int threatY = 0; threatY < 8; threatY++){
						for(int threatX = 0; threatX < 8; threatX++){
							Quadrado threatSquare = tabuleiro[threatY][threatX];
							
							if((threatSquare.getType() == "blank") && (threatSquare.getColor() == plyColor)){
								int[] moveFrom = {threatX, threatY};
								int[] moveTo = {kingX, kingY};
							
								if(threatSquare.checkMove(moveFrom, moveTo, plyColor, true)){
									return "check";
								}
							}
						}
					}
				}
			}
		}*/
		
		return null;
	}
	
	private static void update(int[] origLoc, int[] newLoc){ 
		tabuleiro[newLoc[1]][newLoc[0]] = tabuleiro[origLoc[1]][origLoc[0]];
		tabuleiro[origLoc[1]][origLoc[0]] = new Vazio();
	}
	
	private static void draw(){
		System.out.print("\n   ");
		
		for(char i: SIDE_LETTERS){ 
			System.out.print("  " + i + "  ");
		}
		System.out.print("\n   ");
		
		for(int i = 0; i < 8; i++){
			System.out.print(" --- ");
		}
		
		System.out.print("\n");
		for(int i = 0; i < 8; i++){ 
			System.out.print(" " + (8 - i) + " "); 
			
			for(Quadrado j: tabuleiro[i]){
				System.out.print("|" + j.getSymbol() + "|");
			}
			System.out.print(" " + (8 - i) + " "); 
			
			System.out.print("\n   ");
			
			for(int j = 0; j < 8; j++){
				System.out.print(" --- ");
			}
			System.out.print("\n");
		}
		System.out.print("   ");
		for(char i: SIDE_LETTERS){ 
			System.out.print("  " + i + "  ");
		}
		System.out.print("\n\n");
	}
	
	
	private static String getName(int playerNum, String prevName){ 
		String name;
		
		//while(true){
			//System.out.print("Player " + playerNum + " digite seu nome.\n>> ");
			name = scanner.nextLine().trim();
			
			
			//if(!name.isEmpty() && !(name.contains(" ") || name.contains("\t")) && !name.equals(prevName)) 
				
				//break; 
			//else
				//System.out.println("Nome invalido, tente novamente.");
		//}
		return name;
	}
	
	private static boolean quit(Jogador player) {
		//System.out.print("Player  "+  player.name  + "  se deseja desistir, digite 'desisto'.\n");
		String Quit;
		Quit = scanner.nextLine();
		if(Quit.equals("Desisto")||Quit.equals("desisto")) {
			return true;
		}
		else {
			return false;
		}
	}
	/*public static boolean BigRoqueWhite() {
		if(tabuleiro[7][4].getType()=="king" && tabuleiro[7][0].getType()=="rook") {
			if(tabuleiro[7][1].getType()=="blank" && tabuleiro[7][2].getType()=="blank" && tabuleiro[7][3].getType()=="blank" ) {
					return true;
			}
			
	}
		return false;
	}	
	public static boolean SmallRoqueWhite() {
		if(tabuleiro[7][4].getType()=="king" && tabuleiro[7][7].getType()=="rook") {
			if(tabuleiro[7][5].getType()=="blank" && tabuleiro[7][6].getType()=="blank" ) {
					return true;
			}
		
		}
		return false;		
	}
	public static boolean BigRoqueBlack() {
		if(tabuleiro[0][4].getType()=="king" && tabuleiro[0][0].getType()=="rook" ) {
			if(tabuleiro[0][1].getType()=="blank" && tabuleiro[0][2].getType()=="blank" && tabuleiro[0][3].getType()=="blank") {
				return true;
			}
		
		}
	return false;
	}
	public static boolean SmallRoqueBlack() {
		if(tabuleiro[0][4].getType()=="king" && tabuleiro[0][7].getType()=="rook" ) {
			if(tabuleiro[0][5].getType()=="blank" && tabuleiro[0][6].getType()=="blank") {
					return true;
			}
		}
		return false;
	}
	public void promotePeao(int moveFromX,int moveFromY ,int moveToY,int moveToX,Jogador x) {
		if(tabuleiro[moveFromX][moveFromY].getColor()=="black") {
		if(moveToY==7 && tabuleiro[moveFromY][moveFromX].getType()=="pawn") {
			System.out.println("Escolha uma peça para ser a substituida pelo peao promovido: D = Dama, B = Bispo, C = Cavalo, T = Torre");
			String opt=scanner.nextLine();
			if(opt=="D") {
				tabuleiro[moveToY][moveToX].type="queen";
			}
			if(opt=="B") {
				tabuleiro[moveToY][moveToX].type="bishop";
			}
			if(opt=="C") {
				tabuleiro[moveToY][moveToX].type="knight";
			}
			if(opt=="T") {
				tabuleiro[moveToY][moveToX].type="rook";
			}
		}
	}
		else if(tabuleiro[moveFromX][moveFromY].getColor()=="white"){
			if(moveToY==0 && tabuleiro[moveFromX][moveFromY].getType()=="pawn"  ) {
				System.out.println("Escolha uma peça para ser a substituida pelo peao promovido: D = Dama, B = Bispo, C = Cavalo, T = Torre");
				String opt=scanner.nextLine();
				if(opt=="D") {
					tabuleiro[moveToY][moveToX].type="queen";
				}
				if(opt=="B") {
					tabuleiro[moveToY][moveToX].type="bishop";
				}
				if(opt=="C") {
					tabuleiro[moveToY][moveToX].type="knight";
				}
				if(opt=="T") {
					tabuleiro[moveToY][moveToX].type="rook";
				}
			}
		}
	}*/	
        
	public static void main(String[] args) {
            
            
            Interface i = new Interface();
            i.show();
            
            
            
            
		//System.out.println("=====> CHESS <====="); //title
		
		String ply1Name = getName(1, null);
		String ply2Name = getName(2, ply1Name);
		
		Jogador whitePly = new Jogador(ply1Name, "white");
		Jogador blackPly = new Jogador(ply2Name, "black");
		
		setup(); //get board 
		
		
		while(true){
			
			for(int runNum = 1; runNum <= 2; runNum++){ 
				draw(); 
				
				int move[][] = new int[2][2];
				
				while(true){
				
					if(runNum == 1){ 
						move = whitePly.getMove();
						
						
					}
					else{ 
						move = blackPly.getMove();
						
					}
				
					if(move[0][0] == -1){ 
						//System.out.println("Localização invalida. Tente novamente.");
						continue;
					}
					
					
					int[] moveFrom = move[0];
					int[] moveTo = move[1];
					Quadrado fromSquare = tabuleiro[moveFrom[1]][moveFrom[0]];
				
					boolean checkValue;
					if(runNum == 1){
						checkValue = fromSquare.checkMove(moveFrom, moveTo, "white", false); 
					}
					else{
						checkValue = fromSquare.checkMove(moveFrom, moveTo, "black", false);
					}
					try {
					if(checkValue){
						update(moveFrom, moveTo);
						
						if(runNum == 1){
							if (checkForCheckOrMate("white") == "check"){
								//System.out.println("Check!");
							}
						}
						else{
							if (checkForCheckOrMate("black") == "check"){
								//System.out.println("Check!");
							}
						}
						break;
						
					}
					throw new InvalidMoveException();
					}catch(InvalidMoveException e) {
						System.out.println(e.getMessage());
					}
				}
                                
				if(quit(whitePly)==true) {
					break;
				}
				if(quit(blackPly)==true) {
					break;
				}
                                
				//int contador=0;
				/*if(SmallRoqueWhite()==true||BigRoqueWhite()==true && contador<1) {
					 
					System.out.println(whitePly.name+" Deseja fazer o roque? Se sim digite 'sim'");
					String option=scanner.nextLine();
					if((option=="sim"||option=="Sim") && SmallRoqueWhite()==true) {
						tabuleiro[7][2]= tabuleiro[7][4];
						tabuleiro[7][3]= tabuleiro[7][0];
						tabuleiro[7][0]= new Vazio();
						tabuleiro[7][4]= new Vazio();
					}
					else if((option=="sim"||option=="Sim") && BigRoqueWhite()==true) {
						tabuleiro[7][6]= tabuleiro[7][4];
						tabuleiro[7][5]= tabuleiro[7][7];
						tabuleiro[7][7]= new Vazio();
						tabuleiro[7][4]= new Vazio();
					}
					contador++;
					System.out.println(contador);
					}
					
					
				}
				int cont=0;
				if(BigRoqueBlack()==true||SmallRoqueBlack()==true && cont<1) {
					System.out.println(blackPly.name+" Deseja fazer o roque?Se sim digite 'sim'");
					String option=scanner.nextLine();
					if(option=="sim"||option=="Sim") {
						tabuleiro[0][2]= tabuleiro[0][4];
						tabuleiro[0][3]= tabuleiro[0][0];
						tabuleiro[0][4]= new Vazio();
						tabuleiro[0][0]= new Vazio();
					}
					else if((option=="sim"||option=="Sim") && BigRoqueWhite()==true) {
						tabuleiro[0][6]= tabuleiro[0][4];
						tabuleiro[0][5]= tabuleiro[0][7];
						tabuleiro[0][7]= new Vazio();
						tabuleiro[0][4]= new Vazio();
					}
					cont++;	
					System.out.println(cont);
					}*/	
		}
	}
}
}