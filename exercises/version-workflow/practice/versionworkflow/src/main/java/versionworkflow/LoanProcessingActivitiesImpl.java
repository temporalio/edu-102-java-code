package versionworkflow;

import versionworkflow.model.ChargeInput;
import versionworkflow.model.CustomerInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoanProcessingActivitiesImpl implements LoanProcessingActivities {

    private static final Logger logger = LoggerFactory.getLogger(LoanProcessingActivitiesImpl.class);

    @Override
    public String chargeCustomer(ChargeInput input) {
        String customerID = input.getCustomerID();
        int amount = input.getAmount();
        int numberOfPeriods = input.getNumberOfPeriods();

        logger.info(
                "*** Charging customer***: CustomerID: {}, Amount {}, Number of Periods: {}",
                customerID, amount, numberOfPeriods);

        // pretend we charge them

        String confirmation = String.format("Charged %d to customer '%s'", amount, customerID);

        return confirmation;

    }

    @Override
    public String sendThankYouToCustomer(CustomerInfo input) {

        String customerID = input.getCustomerID();
        String email = input.getEmailAddress();
        

        logger.info(
                "*** Sending thank you message to Customer ***: CustomerID: {}, Email {}",
                customerID, email);

        String confirmation = String.format("Sent thank you message to customer '%s'", customerID);

        return confirmation;
    }

}
