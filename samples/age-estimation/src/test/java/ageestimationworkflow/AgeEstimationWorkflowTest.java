package ageestimationworkflow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.testing.TestWorkflowExtension;
import io.temporal.worker.Worker;

public class AgeEstimationWorkflowTest {

  @RegisterExtension
  public static final TestWorkflowExtension testWorkflowExtension = 
      TestWorkflowExtension.newBuilder()
          .setWorkflowTypes(AgeEstimationWorkflowImpl.class)
          .setDoNotStart(true)
          .build();

  @Test
  public void testSuccessfulAgeEstimation(TestWorkflowEnvironment testEnv, Worker worker,
      AgeEstimationWorkflow workflow) {

    worker.registerActivitiesImplementations(new AgeEstimationActivitiesImpl());
    testEnv.start();

    String result = workflow.estimateAge("Betty");

    assertEquals("Betty has an estimated age of 76", result);
  }
}
