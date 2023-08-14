package versionworkflow;

import io.temporal.WorkflowInterface;
import io.temporal.WorkflowMethod;

import versionworkflow.model.CustomerInfo;

@WorkflowInterface
public interface LoanProcessingWorkflow {

    @WorkflowMethod
    public String loanProcessingWorkflow(CustomerInfo info);

}
