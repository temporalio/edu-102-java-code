package ageestimationworkflow;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface AgeEstimationActivities {

  int retrieveEstimate(String name);
}
