package Exceptions;


public class InvalidRoqueException extends Exception{
    private static final long serialVersionUID = 1L;
        
	public InvalidRoqueException(){
	    super("Jogada Invalida. Tente novamente:");
	}
	public InvalidRoqueException(String message) {
	    super(message);
	}
}
