package versionworkflow;

import versionworkflow.model.ChargeInput;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoanProcessingActivitiesImpl implements LoanProcessingActivities {

    private static final Logger logger = LoggerFactory.getLogger(LoanProcessingActivitiesImpl.class);

    @Override
    public String chargeCustomer(ChargeInput input) {
        String customerID = input.getCustomerID();
        String name = input.getName();
        int amount = input.getAmount();
        int numberOfPeriods = input.getNumberOfPeriods();

        logger.info(
                "*** Charging customer***: CustomerID: {}, Name {}, Amount {}, Number of Periods: {}",
                customerID, name, amount, numberOfPeriods);

        // pretend we charge them

        String confirmation = String.format("Charged %d to customer '%s'", amount, customerID);

        return confirmation;

    }

    @Override
    public String sendThankYouToCustomer(CustomerInput input) {

        String name = input.getName();
        String email = input.getEmailAddress();

        logger.info(
                "*** Sending thank you message to Customer ***: CustomerID: {}, Email {}",
                customerID, email);

        String confirmation = String.format("Sent thank you message to customer '%s'", customerID);

        return confirmation;
    }

}
