package translationworkflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import translationworkflow.model.TranslationActivityInput;
import translationworkflow.model.TranslationActivityOutput;
import translationworkflow.model.TranslationWorkflowInput;
import translationworkflow.model.TranslationWorkflowOutput;
import org.slf4j.Logger;

// TODO: Part 3 - Add the Duration import here

import java.time.Duration;

public class TranslationWorkflowImpl implements TranslationWorkflow {

  // TODO: Define the Workflow logger

  ActivityOptions options =
      ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(5)).build();

  private final TranslationActivities activities =
      Workflow.newActivityStub(TranslationActivities.class, options);

  @Override
  public TranslationWorkflowOutput sayHelloGoodbye(TranslationWorkflowInput input) {
    String name = input.getName();
    String languageCode = input.getLanguageCode();

    // TODO: Add a log statement at the info level stating that the Workflow has been invoked
    // Be sure to include variable information

    // TODO: Add a log statement at the debug level stating that the Activity has been invoked
    // Be sure to include variable information
    TranslationActivityInput helloInput = new TranslationActivityInput("hello", languageCode);
    TranslationActivityOutput helloResult = activities.translateTerm(helloInput);
    String helloMessage = helloResult.getTranslation() + ", " + name;

    // Wait a little while before saying goodbye
    // TODO: Part C - Add a log statement at the info level stating "Sleeping between translation
    // calls"
    // TODO: Part C - Use Workflos.sleep to create a timer here for 30s

    // TODO: Add a log statement here at the debug level stating that the Activity has been invoked
    // Be sure to include variable information
    TranslationActivityInput goodbyeInput = new TranslationActivityInput("goodbye", languageCode);
    TranslationActivityOutput goodbyeResult = activities.translateTerm(goodbyeInput);
    String goodbyeMessage = goodbyeResult.getTranslation() + ", " + name;

    return new TranslationWorkflowOutput(helloMessage, goodbyeMessage);
  }
}
