package pizzaworkflow.model;

public class Bill {

  private int customerID;
  private String orderNumber;
  private String description;
  private int amount;

  public Bill() {
  }

  public Bill(int customerID, String orderNumber, String description, int amount) {
    this.customerID = customerID;
    this.orderNumber = orderNumber;
    this.description = description;
    this.amount = amount;
  }

  public int getCustomerID() {
    return customerID;
  }

  public void setCustomerID(int customerID) {
    this.customerID = customerID;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(String orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }
}
