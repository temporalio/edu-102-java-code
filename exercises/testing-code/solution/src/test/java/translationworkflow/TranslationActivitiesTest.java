package translationworkflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.temporal.failure.ActivityFailure;
import io.temporal.testing.TestActivityEnvironment;
import translationworkflow.TranslationActivities;
import translationworkflow.TranslationActivitiesImpl;
import translationworkflow.model.TranslationActivityInput;
import translationworkflow.model.TranslationActivityOutput;

public class TranslationActivitiesTest {

  private TestActivityEnvironment testEnvironment;

  @BeforeEach
  public void init() {
    testEnvironment = TestActivityEnvironment.newInstance();
  }

  @AfterEach
  public void destroy() {
    testEnvironment.close();
  }

  @Test
  public void testSuccessfulTranslateActivityHelloGerman() {
    testEnvironment.registerActivitiesImplementations(new TranslationActivitiesImpl());
    TranslationActivities activity = testEnvironment.newActivityStub(TranslationActivities.class);
    TranslationActivityInput input = new TranslationActivityInput("hello", "de");
    TranslationActivityOutput output = activity.translateTerm(input);
    assertEquals("Hallo", output.getTranslation());
  }

  @Test
  public void testSuccessfulTranslateActivityHelloLatvian() {
    testEnvironment.registerActivitiesImplementations(new TranslationActivitiesImpl());
    TranslationActivities activity = testEnvironment.newActivityStub(TranslationActivities.class);
    TranslationActivityInput input = new TranslationActivityInput("goodbye", "lv");
    TranslationActivityOutput output = activity.translateTerm(input);
    assertEquals("Ardievu", output.getTranslation());
  }

  @Test
  public void testFailedTranslateActivityBadLanguageCode() {
    testEnvironment.registerActivitiesImplementations(new TranslationActivitiesImpl());
    TranslationActivities activity = testEnvironment.newActivityStub(TranslationActivities.class);
    TranslationActivityInput input = new TranslationActivityInput("goodbye", "xq");

    // Assert that an error was thrown and it was an Activity Failure
    Exception exception = assertThrows(ActivityFailure.class, () -> {
      TranslationActivityOutput output = activity.translateTerm(input);
    });

    // Assert that the error has the expected message, which identifies
    // the invalid language code as the cause
    assertTrue(exception.getMessage().contains(
        "An error was caught attempting to call the microservice: Error: Invalid language code \'xq\'"),
        "expected error message");
  }
}
