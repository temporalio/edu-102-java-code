package translationworkflow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.testing.TestWorkflowExtension;
import io.temporal.worker.Worker;
import translationworkflow.model.TranslationActivityInput;
import translationworkflow.model.TranslationActivityOutput;
import translationworkflow.model.TranslationWorkflowInput;
import translationworkflow.model.TranslationWorkflowOutput;

public class TranslationWorkflowTest {

  @RegisterExtension
  public static final TestWorkflowExtension testWorkflowExtension = 
      TestWorkflowExtension.newBuilder()
          .setWorkflowTypes(TranslationWorkflowImpl.class)
          .setDoNotStart(true)
          .build();

  @Test
  public void testSuccessfulTranslation(TestWorkflowEnvironment testEnv, Worker worker,
      TranslationWorkflow workflow) {

    worker.registerActivitiesImplementations(new TranslationActivitiesImpl());
    testEnv.start();

    TranslationWorkflowOutput output =
        workflow.sayHelloGoodbye(new TranslationWorkflowInput("Pierre", "fr"));

    assertEquals("Bonjour, Pierre", output.getHelloMessage());
    assertEquals("Au revoir, Pierre", output.getGoodbyeMessage());
  }
}
