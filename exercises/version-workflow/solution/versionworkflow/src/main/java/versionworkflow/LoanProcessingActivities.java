package versionworkflow;

import io.temporal.activity.ActivityInterface;
import versionworkflow.model.ChargeInput;

public interface LoanProcessingActivities {

    public String chargeCustomer(ChargeInput input);

    public String sendThankYouToCustomer(CustomerInput input);

}
