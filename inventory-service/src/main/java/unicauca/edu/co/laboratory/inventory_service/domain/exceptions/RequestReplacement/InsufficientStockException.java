package unicauca.edu.co.laboratory.inventory_service.domain.exceptions.RequestReplacement;

public class InsufficientStockException extends RuntimeException {
  public InsufficientStockException(String message) {
    super(message);
  }
}
