package pizzaworkflow.model;

public class OrderConfirmation {

  private String orderNumber;
  private String status;
  private String confirmationNumber;
  private long billingTimestamp;
  private int amount;

  public OrderConfirmation() {
  }

  public OrderConfirmation(String orderNumber, String status, String confirmationNumber,
      long billingTimestamp, int amount) {

    this.orderNumber = orderNumber;
    this.status = status;
    this.confirmationNumber = confirmationNumber;
    this.billingTimestamp = billingTimestamp;
    this.amount = amount;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getConfirmationNumber() {
    return confirmationNumber;
  }

  public void setConfirmationNumber(String confirmationNumber) {
    this.confirmationNumber = confirmationNumber;
  }

  public long getBillingTimestamp() {
    return billingTimestamp;
  }

  public void setBillingTimestamp(long billingTimestamp) {
    this.billingTimestamp = billingTimestamp;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "OrderConfirmation{" + "orderNumber='" + orderNumber + '\'' + ", status='" + status
        + '\'' + ", confirmationNumber='" + confirmationNumber + '\'' + ", billingTimestamp="
        + billingTimestamp + ", amount=" + amount + '}';
  }
}
