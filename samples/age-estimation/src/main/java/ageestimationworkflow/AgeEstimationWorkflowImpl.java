package ageestimationworkflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class AgeEstimationWorkflowImpl implements AgeEstimationWorkflow {

  private final ActivityOptions options =
      ActivityOptions.newBuilder()
          .setStartToCloseTimeout(Duration.ofSeconds(5))
          .build();

  private final AgeEstimationActivities activities =
      Workflow.newActivityStub(AgeEstimationActivities.class, options);

  @Override
  public String estimateAge(String name) {

    int age = activities.retrieveEstimate(name);

    return String.format("%s has an estimated age of %d", name, age);
  }
}
