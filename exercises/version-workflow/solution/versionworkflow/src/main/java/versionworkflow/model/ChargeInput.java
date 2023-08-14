package versionworkflow.model;

public class ChargeInput {
    private String customerID;
    private int amount;
    private int periodNumber;
    private int numberOfPeriods;

    public ChargeInput(String customerID, int amount, int periodNumber, int numberOfPeriods) {
        this.customerID = customerID;
        this.amount = amount;
        this.periodNumber = periodNumber;
        this.numberOfPeriods = numberOfPeriods;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(int periodNumber) {
        this.periodNumber = periodNumber;
    }

    public int getNumberOfPeriods() {
        return numberOfPeriods;
    }

    public void setNumberOfPeriods(int numberOfPeriods) {
        this.numberOfPeriods = numberOfPeriods;
    }
}
