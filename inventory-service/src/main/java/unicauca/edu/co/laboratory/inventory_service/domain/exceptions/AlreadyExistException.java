package unicauca.edu.co.laboratory.inventory_service.domain.exceptions;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String message) {
        super(message);
    }
}
