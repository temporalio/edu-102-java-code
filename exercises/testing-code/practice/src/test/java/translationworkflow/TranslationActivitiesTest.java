package translationworkflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// import io.temporal.failure.ActivityFailure;
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

  // TODO: Part B - Add the test case for testSuccessfulTranslateActivityGoodbyeLatvian

  // TODO: Part C - Add the code snippet for testing testFailedTranslateActivityBadLanguageCode
}
