package Exceptions;

public class InvalidMoveException extends Exception {
	private static final long serialVersionUID = 1L;
        
	public InvalidMoveException(){
	    super("Jogada Inválida. Tente novamente:");
	}
	public InvalidMoveException(String message) {
	    super(message);
	}
}
