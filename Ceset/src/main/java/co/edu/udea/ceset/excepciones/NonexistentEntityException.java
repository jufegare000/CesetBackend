package co.edu.udea.ceset.excepciones;

public class NonexistentEntityException extends RuntimeException {

    private static final long serialVersionUID = -3413267418209245795L;
    
    public NonexistentEntityException(String message, Throwable cause) {
        super(message, cause);
    }
    public NonexistentEntityException(String message) {
        super(message);
    }
}
