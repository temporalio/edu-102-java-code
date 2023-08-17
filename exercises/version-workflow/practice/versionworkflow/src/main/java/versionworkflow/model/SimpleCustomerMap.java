package versionworkflow.model;

import java.util.HashMap;
import java.util.Map;

public class SimpleCustomerMap implements CustomerInfoDatabase {
  private Map<String, CustomerInfo> customers;

  public SimpleCustomerMap() {
    populate();
  }

  private void populate() {
    customers = new HashMap<>();

    CustomerInfo customer01 = new CustomerInfo("a100", "Ana Garcia", "ana@example.com", 500, 10);
    CustomerInfo customer02 = new CustomerInfo("a101", "Amit Singh", "asingh@example.com", 250, 15);
    CustomerInfo customer03 =
        new CustomerInfo("a102", "Mary O'Connor", "marymo@example.com", 425, 12);

    customers.put(customer01.getCustomerID(), customer01);
    customers.put(customer02.getCustomerID(), customer02);
    customers.put(customer03.getCustomerID(), customer03);
  }

  @Override
  public CustomerInfo get(String customerID) {
    CustomerInfo info = customers.get(customerID);
    if (info == null) {
      throw new RuntimeException("Customer ID does not exist in the database");
    }

    return info;
  }
}
