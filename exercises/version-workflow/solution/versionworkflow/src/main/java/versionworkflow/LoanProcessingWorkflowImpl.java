package versionworkflow;

import org.slf4j.Logger;

public class LoanProcessingWorkflowImpl implements LoanProcessingWorkflow {

    public static final Logger logger = Workflow.getLogger(LoanProcessingWorkflowImpl.class);

    ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .build();

    private final PizzaActivities activities = Workflow.newActivityStub(LoanProcessingActivities.class,
            options);

    public String loanProcessingWorkflow(CustomerInfo info) {

    }

}
