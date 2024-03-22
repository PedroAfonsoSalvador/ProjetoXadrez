package Pecas;

import Jogo.Quadrado;

public abstract class Peca extends Quadrado {
    public Peca(String colorIn, String typeIn) {
	super(typeIn);
	color = colorIn;
    } 
}
