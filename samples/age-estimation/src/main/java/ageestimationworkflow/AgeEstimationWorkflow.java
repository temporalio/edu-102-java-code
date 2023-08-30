package ageestimationworkflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface AgeEstimationWorkflow {

  @WorkflowMethod
  String estimateAge(String name);

}
