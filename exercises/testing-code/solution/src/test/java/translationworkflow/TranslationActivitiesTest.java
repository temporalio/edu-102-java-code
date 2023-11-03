package translationworkflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.temporal.failure.ActivityFailure;
import io.temporal.testing.TestActivityEnvironment;
import translationworkflow.model.TranslationActivityInput;
import translationworkflow.model.TranslationActivityOutput;

public class TranslationActivitiesTest {

  private TestActivityEnvironment testEnvironment;
  private TranslationActivities activity;

  @BeforeEach
  public void init() {
    testEnvironment = TestActivityEnvironment.newInstance();
    testEnvironment.registerActivitiesImplementations(new TranslationActivitiesImpl());
    activity = testEnvironment.newActivityStub(TranslationActivities.class);
  }

  @AfterEach
  public void destroy() {
    testEnvironment.close();
  }

  @Test
  public void testSuccessfulTranslateActivityHelloGerman() {
    TranslationActivityInput input = new TranslationActivityInput("hello", "de");
    TranslationActivityOutput output = activity.translateTerm(input);
    assertEquals("Hallo", output.getTranslation());
  }

  @Test
  public void testSuccessfulTranslateActivityHelloLatvian() {
    TranslationActivityInput input = new TranslationActivityInput("goodbye", "lv");
    TranslationActivityOutput output = activity.translateTerm(input);
    assertEquals("Ardievu", output.getTranslation());
  }

  @Test
  public void testFailedTranslateActivityBadLanguageCode() {
    TranslationActivityInput input = new TranslationActivityInput("goodbye", "xq");

    // Assert that an error was thrown and it was an Activity Failure
    Exception exception = assertThrows(ActivityFailure.class, () -> {
      TranslationActivityOutput output = activity.translateTerm(input);
    });

    // Assert that the error contains the expected message
    assertTrue(exception.getMessage().contains(
        "Invalid language code"),
        "expected error message");
  }
}
