package Jogo;

import Exceptions.CheckMoveException;
import Exceptions.InvalidMoveException;
import static Jogo.Interface.getMove;
import Pecas.*; 
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;



public class Interface extends javax.swing.JFrame {
    public static final char SIDE_LETTERS[] = {'A','B','C','D','E','F','G','H'};
    public static final int SIDE_NUMS[] = {1,2,3,4,5,6,7,8};
    public static Quadrado tabuleiro[][] = new Quadrado[8][8];
    static int cont = 0, cont2 = 0, pontosJPreto = 0, pontosJBranco = 0;
    public static int[] mov = new int[4];
    
    Jogador whitePly = new Jogador(getName("W"), "white");
    Jogador blackPly = new Jogador(getName("B"), "black");
    
    
    
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
    
    
    private static void update(int[] origLoc, int[] newLoc){ 
	tabuleiro[newLoc[1]][newLoc[0]] = tabuleiro[origLoc[1]][origLoc[0]];
	tabuleiro[origLoc[1]][origLoc[0]] = new Vazio();
        
        mov[0] = newLoc[1];
        mov[1] = newLoc[0];
        mov[2] = origLoc[1];
        mov[3] = origLoc[0];  
    }
    
    
    
    private void draw(){		
	for(char i: SIDE_LETTERS){ 
            this.printaTabuleiro.append("       " + i + " "); 
	}
        
	this.printaTabuleiro.append("\n   "); 
        
	this.printaTabuleiro.append("    ---      ---       ---       ---       ---       ---       ---       ---"); 
	
		
	this.printaTabuleiro.append("\n"); //System.out.print("\n");
        
	for(int i = 0; i < 8; i++){ 
		this.printaTabuleiro.append(" " + (8 - i) + " "); 
			
		for(Quadrado j: tabuleiro[i]){
		    this.printaTabuleiro.append("| " + j.getSymbol() + " |"); 
		}
		this.printaTabuleiro.append(" " + (8 - i) + " "); 
		
		this.printaTabuleiro.append("\n   "); 
		
		this.printaTabuleiro.append("    ---      ---       ---       ---       ---       ---       ---       ---");
		
		this.printaTabuleiro.append("\n"); 
	}
	this.printaTabuleiro.append("   "); 
        
	for(char i: SIDE_LETTERS){ 
	    this.printaTabuleiro.append("     " + i + "   "); 
	}
    }
    
    
    
    public String getName(String cor){ 
        String name="";
        
        if(cor.equals("W")){
            while(true){
                name = JOptionPane.showInputDialog(this, "Primeiro Jogador, digite o seu nome: ");
                
	        if(!name.isEmpty() && !(name.contains(" ") || name.contains("\t"))){break;} // && !name.equals(prevName)
                else{JOptionPane.showMessageDialog(this, "Nome inválido, tente novamente.");}
            }
        }
        else{
           while(true){
                name = JOptionPane.showInputDialog(this, "Segundo Jogador, digite o seu nome: ");
                
	        if(!name.isEmpty() && !(name.contains(" ") || name.contains("\t"))){ break; } // && !name.equals(prevName)
                else{ JOptionPane.showMessageDialog(this, "Nome inválido, tente novamente."); }
                
            } 
        }
        
	return name;
    }
    
    
    public void atualizarNome(){
        if(cont2 % 2 != 0){
            this.jogadorVezNome.setText(whitePly.getNomeBranco());
            cont2++;
        }
        else{
            this.jogadorVezNome.setText(blackPly.getNomePreto());
            cont2++;
        }
    }
    
    
    
    public void atualizarPontos(String cor){
        if(cor.equals("W") == true){
            pontosJBranco++;
            String pb = String.valueOf(pontosJBranco);
            this.pontosBranco.setText(pb);
        }
        else{
            pontosJPreto++;
            String pp = String.valueOf(pontosJPreto);
            this.pontosPreto.setText(pp);
        }
    }
    
    
    
    public void quit(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }
    
    
    
    public void mover(Jogador jogada){   ;      
        
        this.printaTabuleiro.setText(""); //Limpa a tela
        draw();
        
        int move[][] = new int[2][2];
	
        
        while(true){		
	    if(jogada == whitePly){ 
		move = whitePly.getMove();
            }
	    else{ 
		move = blackPly.getMove();	
	    }
		
            
	    if(move[0][0] == -1){ 
		JOptionPane.showMessageDialog(this, "Localização invalida. Tente novamente.");				
		continue;
	    }
					
					
	    int[] moveFrom = move[0];
	    int[] moveTo = move[1];
	    Quadrado fromSquare = tabuleiro[moveFrom[1]][moveFrom[0]];
				
	    boolean checkValue;
            
	    if(jogada == whitePly){
		checkValue = fromSquare.checkMove(moveFrom, moveTo, "white", false); 
	    }
	    else{
		checkValue = fromSquare.checkMove(moveFrom, moveTo, "black", false);
	    }
            
	    try{
                if(jogada == whitePly) {
		    if(checkForCheck("white")=="check") {
		        throw new CheckMoveException();
		    }
		    else if(checkForCheck("black")=="check"){
		        throw new CheckMoveException();
		    }
	        }
	    }catch(CheckMoveException er) {
                //JOptionPane.showMessageDialog(this, er.getMessage());	
	    }
            
	    try {
	        if(checkValue){
                    //Promoção dos Peões
                    if(Interface.tabuleiro[moveFrom[1]][moveFrom[0]].getType().equals("pawn") == true  && jogada == whitePly && moveTo[1] == 0){
                        promotePeao("W", moveTo[0], moveFrom[1], moveFrom[0]);
                    }
                    else if(Interface.tabuleiro[moveFrom[1]][moveFrom[0]].getType().equals("pawn") == true && jogada == blackPly && moveTo[1] == 7){
                        promotePeao("B", moveTo[0], moveFrom[1], moveFrom[0]);  
                    }
                    
                    else{
		        update(moveFrom, moveTo);
                    }	
                    
                    
		    if(jogada == whitePly){
			if (checkForCheck("white") == "check"){
			    JOptionPane.showMessageDialog(this, "Check!");
			}
		    }
                    
		    else{
			if (checkForCheck("black") == "check"){
                            JOptionPane.showMessageDialog(this, "Check!");
			}
		    }
                    
		    break;
						
		}
                
		throw new InvalidMoveException();
                
	    }catch(InvalidMoveException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
	    }			
	}
        
        
        this.printaTabuleiro.setText(""); //Limpa a tela
        draw();
    }
    
    
    
    public static boolean kingCanMove(String plyColor) {
	int localx = 0;
	int localy = 0;
	for(int y=0;y<8;y++) {
	    for(int x=0;x<8;x++) {
		if(tabuleiro[y][x].getType().equals("king") == true) {
		    if(tabuleiro[y][x].getColor().equals(plyColor) == true){
			localx = x;
			localy = y;
		    }
		}
	    }
	}
        
	for(int i=1;i<2;i++) {
	    for(int j=1;j<2;j++) {
		localx-=1;
		localy-=1;
		if(tabuleiro[localx+i][localy+j].getType().equals("blank") == true) {
		    return false;
		}
	    }
	}
        
	return true;
    }
    
    
    
    private static String checkForCheck(String plyColor){ 
		for(int kingY = 0; kingY < 8; kingY++){
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
							
							if((threatSquare.getType() == "rook")||((threatSquare.getType() == "queen")||(threatSquare.getType() == "bishop")||(threatSquare.getType() == "pawn")||(threatSquare.getType() == "knight")) && (threatSquare.getColor() == plyColor)){
								int[] moveFrom = {threatY, threatX};
								int[] moveTo = {kingY, kingX};
							
								if(threatSquare.checkMove(moveFrom, moveTo, plyColor, true)){
									return "check";
								}
							}
						}
					}
				}
			}
		}
		
		return "";
	}
 
    
    
    public static void promotePeao(String cor, int x, int yLocal, int xLocal){
        String[] promote = {"","Dama", "Bispo", "Cavalo", "Torre"};
        JComboBox jcb = new JComboBox(promote);
        
        
        JOptionPane.showMessageDialog(null, "Seu peão vai ser promovido!");
        JOptionPane.showMessageDialog(null, jcb, "Selecione o novo tipo desse peão: ", JOptionPane.QUESTION_MESSAGE);
        
        
        if(jcb.getSelectedItem().equals("Dama") == true){
            if(cor.equals("W") == true){
                Interface.tabuleiro[0][x] = new Dama("white");
                Interface.tabuleiro[yLocal][xLocal] = new Vazio();
            }
            else{
                Interface.tabuleiro[7][x] = new Dama("black");
                Interface.tabuleiro[yLocal][xLocal] = new Vazio();
            }
        }
        
        else if(jcb.getSelectedItem().equals("Bispo") == true){
            if(cor.equals("W") == true){
                Interface.tabuleiro[0][x] = new Bispo("white");
                Interface.tabuleiro[yLocal][xLocal] = new Vazio();
            }
            else{
                Interface.tabuleiro[7][x] = new Bispo("black");
                Interface.tabuleiro[yLocal][xLocal] = new Vazio();
            }
        }
        
        else if(jcb.getSelectedItem().equals("Cavalo") == true){
            if(cor.equals("W") == true){
                Interface.tabuleiro[0][x] = new Cavalo("white");
                Interface.tabuleiro[yLocal][xLocal] = new Vazio();
            }
            else{
                Interface.tabuleiro[7][x] = new Cavalo("black");
                Interface.tabuleiro[yLocal][xLocal] = new Vazio();
            }
        }
        
        else if(jcb.getSelectedItem().equals("Torre") == true){
            if(cor.equals("W") == true){
                Interface.tabuleiro[0][x] = new Torre("white");
                Interface.tabuleiro[yLocal][xLocal] = new Vazio();
            }
            else{
                Interface.tabuleiro[7][x] = new Torre("black");
                Interface.tabuleiro[yLocal][xLocal] = new Vazio();
            }
        }
        
        else{
            promotePeao(cor, x, yLocal, xLocal);
        }
    }
    
    
    
    public static int[] getMove(){
        return mov;
    }
    
    
    
    public void historicoJogadas(int[] mov, String cor){
        String[] lista = {"A", "B", "C", "D", "E", "F", "G", "H"};
        int[] listaInt = {8, 7, 6, 5, 4, 3, 2, 1};
        String peca ="";
        
        
        if(tabuleiro[mov[0]][mov[1]].getType().equals("bishop") == true){peca = "Bispo";}
        else if(tabuleiro[mov[0]][mov[1]].getType().equals("knight") == true){peca = "Cavalo";}
        else if(tabuleiro[mov[0]][mov[1]].getType().equals("queen") == true){peca = "Dama";}
        else if(tabuleiro[mov[0]][mov[1]].getType().equals("pawn") == true){peca = "Peão";}
        else if(tabuleiro[mov[0]][mov[1]].getType().equals("king") == true){peca = "Rei";}
        else if(tabuleiro[mov[0]][mov[1]].getType().equals("rook") == true){peca = "Torre";}
        
        
        if(cor.equals("W") == true){
            this.historicoArea.append(whitePly.getNomeBranco() + " : " + lista[mov[3]] + listaInt[mov[2]] + " -> " + 
                        lista[mov[1]] + listaInt[mov[0]] + " - " + peca + "\n");
        }
        else{
            this.historicoArea.append(blackPly.getNomePreto() + " : " + lista[mov[3]] + listaInt[mov[2]] + " -> " + 
                    lista[mov[1]] + listaInt[mov[0]] + " - " + peca + "\n");
        }
    }
    
    
    
    public Interface() {
        initComponents();
        
        jogadorBrancoNome.setText(whitePly.getNomeBranco());
        jogadorPretoNome.setText(blackPly.getNomePreto());
        
        setup();
        draw(); 
        
        this.jogadorVezNome.setText(whitePly.getNomeBranco()); 
        
        String pb = String.valueOf(pontosJBranco);
        String pp = String.valueOf(pontosJPreto);
        
        this.pontosBranco.setText(pb);
        this.pontosPreto.setText(pp);
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        jogadorBrancoNome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        printaTabuleiro = new javax.swing.JTextArea();
        jogadorVez = new javax.swing.JLabel();
        jogadorVezNome = new javax.swing.JLabel();
        jogar = new javax.swing.JButton();
        desistir = new javax.swing.JButton();
        pontos1 = new javax.swing.JLabel();
        pontos2 = new javax.swing.JLabel();
        pontosPreto = new javax.swing.JLabel();
        pontosBranco = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        historicoArea = new javax.swing.JTextArea();
        jogadorPretoNome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        titulo.setText("=====> CHESS <=====");

        jogadorBrancoNome.setText("jogadorBranco");

        printaTabuleiro.setColumns(20);
        printaTabuleiro.setRows(5);
        printaTabuleiro.setEnabled(false);
        jScrollPane1.setViewportView(printaTabuleiro);

        jogadorVez.setText("Jogador da Vez:");

        jogadorVezNome.setText("jLabel1");

        jogar.setText("Jogar");
        jogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogarActionPerformed(evt);
            }
        });

        desistir.setText("Desistir");
        desistir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desistirActionPerformed(evt);
            }
        });

        pontos1.setText("Pontos:");

        pontos2.setText("Pontos:");

        pontosPreto.setText("0");

        pontosBranco.setText("0");

        historicoArea.setColumns(20);
        historicoArea.setRows(5);
        historicoArea.setEnabled(false);
        jScrollPane3.setViewportView(historicoArea);

        jogadorPretoNome.setText("jLabel1");

        jLabel1.setText("Histórico de Jogadas:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jogadorBrancoNome)
                        .addGap(193, 193, 193)
                        .addComponent(pontos2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pontosBranco))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jogadorPretoNome)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pontos1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pontosPreto))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jogadorVezNome)
                    .addComponent(jogadorVez)
                    .addComponent(jogar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(desistir, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(184, 184, 184)
                    .addComponent(titulo)
                    .addContainerGap(197, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pontosPreto)
                            .addComponent(pontos1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jogadorPretoNome)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jogadorVez)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jogadorVezNome)
                        .addGap(24, 24, 24)
                        .addComponent(jogar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(desistir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pontosBranco)
                    .addComponent(pontos2)
                    .addComponent(jogadorBrancoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 19, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(titulo)
                    .addContainerGap(414, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jogarActionPerformed
        // TODO add your handling code here:
        if(cont % 2 == 0){     
            mover(whitePly);
            cont++;
            atualizarNome();
            
            historicoJogadas(getMove(), "W");
        }
        
        else{
            mover(blackPly);
            cont++;
            atualizarNome();
            
            historicoJogadas(getMove(), "B");
        }
    }//GEN-LAST:event_jogarActionPerformed

    private void desistirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desistirActionPerformed
        // TODO add your handling code here:
        if(jogadorVezNome.getText().equals(whitePly.getNomeBranco())){
            JOptionPane.showMessageDialog(this, "O jogador " + blackPly.getNomePreto() + " venceu!!");
            int revanche = JOptionPane.showConfirmDialog(this, (whitePly.getNomeBranco() + ", deseja uma revanche?"), "Revanche!", JOptionPane.YES_NO_OPTION);
            
            if(revanche == 0){
                int revanche2 = JOptionPane.showConfirmDialog(this, (blackPly.getNomePreto() + ", deseja aceitar a revanche?"), "Revanche!", JOptionPane.YES_NO_OPTION);
                
                if(revanche2 == 0){
                    atualizarPontos("B");
                    this.printaTabuleiro.setText("");
                    setup();
                    draw();
                    this.jogadorVezNome.setText(whitePly.getNomeBranco());
                    this.historicoArea.setText("");
                }
                
                else{
                    JOptionPane.showMessageDialog(this, "O jogador " + blackPly.getNomePreto() + " venceu!!");
                    quit();
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "O jogador " + blackPly.getNomePreto() + " venceu!!");
                quit();
            }
        }
        
        else{
            JOptionPane.showMessageDialog(this, "O jogador " + whitePly.getNomeBranco() + " venceu!!");
            int revanche = JOptionPane.showConfirmDialog(this, (blackPly.getNomePreto() + ", deseja uma revanche?"), "Revanche!", JOptionPane.YES_NO_OPTION);
            
            if(revanche == 0){
                int revanche2 = JOptionPane.showConfirmDialog(this, (whitePly.getNomeBranco() + ", deseja aceitar a revanche?"), "Revanche!", JOptionPane.YES_NO_OPTION);
            
                if(revanche2 == 0){
                    atualizarPontos("W");
                    this.printaTabuleiro.setText("");
                    setup();
                    draw();
                    this.jogadorVezNome.setText(whitePly.getNomeBranco()); 
                    this.historicoArea.setText("");
                }
                
                else{
                    JOptionPane.showMessageDialog(this, "O jogador " + whitePly.getNomeBranco() + " venceu!!");
                    quit();
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "O jogador " + whitePly.getNomeBranco() + " venceu!!");
                quit();
            }
        }
    }//GEN-LAST:event_desistirActionPerformed

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton desistir;
    private javax.swing.JTextArea historicoArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jogadorBrancoNome;
    private javax.swing.JLabel jogadorPretoNome;
    private javax.swing.JLabel jogadorVez;
    private javax.swing.JLabel jogadorVezNome;
    private javax.swing.JButton jogar;
    private javax.swing.JLabel pontos1;
    private javax.swing.JLabel pontos2;
    private javax.swing.JLabel pontosBranco;
    private javax.swing.JLabel pontosPreto;
    private javax.swing.JTextArea printaTabuleiro;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
