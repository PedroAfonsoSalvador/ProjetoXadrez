package Exceptions;

public class CheckMoveException extends Exception{
	private static final long serialVersionUID = 1L;
        
	public CheckMoveException() {
	    super("Seu rei está em Xeque, proteja ele. Jogada inválida");
	}
	public CheckMoveException(String message) {
	    super(message);
	}
}
