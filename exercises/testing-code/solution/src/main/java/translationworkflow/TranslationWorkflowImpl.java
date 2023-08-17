package translationworkflow;

import java.time.Duration;
import org.slf4j.Logger;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import translationworkflow.model.TranslationActivityInput;
import translationworkflow.model.TranslationActivityOutput;
import translationworkflow.model.TranslationWorkflowInput;
import translationworkflow.model.TranslationWorkflowOutput;

public class TranslationWorkflowImpl implements TranslationWorkflow {

  public static final Logger logger = Workflow.getLogger(TranslationWorkflowImpl.class);

  ActivityOptions options =
      ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(5)).build();

  private final TranslationActivities activities =
      Workflow.newActivityStub(TranslationActivities.class, options);

  @Override
  public TranslationWorkflowOutput sayHelloGoodbye(TranslationWorkflowInput input) {
    String name = input.getName();
    String languageCode = input.getLanguageCode();

    logger.info("sayHelloGoodbye Workflow Invoked with input name: {} language code: {}", name,
        languageCode);

    logger.debug("Preparing to translate Hello into languageCode: {}", languageCode);
    TranslationActivityInput helloInput = new TranslationActivityInput("hello", languageCode);
    TranslationActivityOutput helloResult = activities.translateTerm(helloInput);
    String helloMessage = helloResult.getTranslation() + ", " + name;

    // Wait a little while before saying goodbye
    logger.info("Sleeping between translation calls");
    Workflow.sleep(Duration.ofSeconds(30));

    logger.debug("Preparing to translate Goodbye into languageCode: {}", languageCode);
    TranslationActivityInput goodbyeInput = new TranslationActivityInput("goodbye", languageCode);
    TranslationActivityOutput goodbyeResult = activities.translateTerm(goodbyeInput);
    String goodbyeMessage = goodbyeResult.getTranslation() + ", " + name;

    return new TranslationWorkflowOutput(helloMessage, goodbyeMessage);
  }
}
