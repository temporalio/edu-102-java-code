package versionworkflow;

import versionworkflow.model.ChargeInput;
import versionworkflow.model.CustomerInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoanProcessingActivitiesImpl implements LoanProcessingActivities {

  private static final Logger logger = LoggerFactory.getLogger(LoanProcessingActivitiesImpl.class);

  @Override
  public String chargeCustomer(ChargeInput input) {
    String customerId = input.getCustomerID();
    int amount = input.getAmount();
    int numberOfPeriods = input.getNumberOfPeriods();

    logger.info("*** Charging customer***: CustomerID: {}, Amount {}, Number of Periods: {}",
        customerId, amount, numberOfPeriods);

    // pretend we charge them

    String confirmation = String.format("Charged %d to customer '%s'", amount, customerId);

    return confirmation;

  }

  @Override
  public String sendThankYouToCustomer(CustomerInfo input) {

    String customerId = input.getCustomerID();
    String email = input.getEmailAddress();


    logger.info("*** Sending thank you message to Customer ***: CustomerID: {}, Email {}",
        customerId, email);

    String confirmation = String.format("Sent thank you message to customer '%s'", customerId);

    return confirmation;
  }

}
