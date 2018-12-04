package co.edu.udea.ceset.excepciones;

public class PreexistingEntityException extends RuntimeException {

    private static final long serialVersionUID = 4655956078731675605L;
    
    public PreexistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public PreexistingEntityException(String message) {
        super(message);
    }
}
