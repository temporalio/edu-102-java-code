package pizzaworkflow.exceptions;

public class InvalidChargeAmountException extends Exception {

  public InvalidChargeAmountException() {
    super();
  }

  public InvalidChargeAmountException(String message) {
    super(message);
  }

  public InvalidChargeAmountException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidChargeAmountException(Throwable cause) {
    super(cause);
  }
}
