package unicauca.edu.co.laboratory.inventory_service.domain.exceptions.RequestReplacement;

public class InvalidRequestException extends RuntimeException {
  public InvalidRequestException(String message) {
    super(message);
  }
}
