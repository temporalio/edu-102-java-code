package pizzaworkflow.exceptions;

public class OutOfServiceAreaException extends Exception {

  public OutOfServiceAreaException() {
    super();
  }

  public OutOfServiceAreaException(String message) {
    super(message);
  }

  public OutOfServiceAreaException(String message, Throwable cause) {
    super(message, cause);
  }

  public OutOfServiceAreaException(Throwable cause) {
    super(cause);
  }
}
