package ageestimationworkflow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.testing.TestWorkflowExtension;
import io.temporal.worker.Worker;

import static org.mockito.Mockito.*;


public class AgeEstimationWorkflowMockTest {

  @RegisterExtension
  public static final TestWorkflowExtension testWorkflowExtension = 
      TestWorkflowExtension.newBuilder()
          .setWorkflowTypes(AgeEstimationWorkflowImpl.class)
          .setDoNotStart(true)
          .build();

  @Test
  public void testSuccessfulAgeEstimation(TestWorkflowEnvironment testEnv, Worker worker,
      AgeEstimationWorkflow workflow) {

    AgeEstimationActivities mockedActivities =
        mock(AgeEstimationActivities.class, withSettings().withoutAnnotations());
    when(mockedActivities.retrieveEstimate("Stanislav")).thenReturn(68);

    worker.registerActivitiesImplementations(mockedActivities);
    testEnv.start();

    String result = workflow.estimateAge("Stanislav");

    assertEquals("Stanislav has an estimated age of 68", result);
  }
}
