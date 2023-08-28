package ageestimationworkflow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.temporal.testing.TestActivityEnvironment;

public class AgeEstimationActivitiesTest {

  private TestActivityEnvironment testEnvironment;
  private AgeEstimationActivities activities;

  @BeforeEach
  public void init() {
    testEnvironment = TestActivityEnvironment.newInstance();
    testEnvironment.registerActivitiesImplementations(new AgeEstimationActivitiesImpl());
    activities = testEnvironment.newActivityStub(AgeEstimationActivities.class);
  }

  @AfterEach
  public void destroy() {
    testEnvironment.close();
  }

  @Test
  public void testRetrieveEstimate() {
    int result = activities.retrieveEstimate("Mason");
    assertEquals(38, result);
  }
}
