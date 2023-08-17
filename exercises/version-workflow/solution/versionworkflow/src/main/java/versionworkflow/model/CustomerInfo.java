package versionworkflow.model;

public class CustomerInfo {
    private String customerID;
    private String name;
    private String emailAddress;
    private int amount;
    private int numberOfPeriods;

    public CustomerInfo() {

    }

    public CustomerInfo(String customerID, String name, String emailAddress, int amount, int numberOfPeriods) {
        this.customerID = customerID;
        this.name = name;
        this.emailAddress = emailAddress;
        this.amount = amount;
        this.numberOfPeriods = numberOfPeriods;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getNumberOfPeriods() {
        return numberOfPeriods;
    }

    public void setNumberOfPeriods(int numberOfPeriods) {
        this.numberOfPeriods = numberOfPeriods;
    }
}
