package versionworkflow;

import io.temporal.activity.ActivityInterface;
import versionworkflow.model.ChargeInput;
import versionworkflow.model.CustomerInfo;

@ActivityInterface
public interface LoanProcessingActivities {

    public String chargeCustomer(ChargeInput input);

    public String sendThankYouToCustomer(CustomerInfo input);

}
