package translationworkflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import translationworkflow.model.TranslationActivityInput;
import translationworkflow.model.TranslationActivityOutput;
import translationworkflow.model.TranslationWorkflowInput;
import translationworkflow.model.TranslationWorkflowOutput;

import java.time.Duration;

public class TranslationWorkflowImpl implements TranslationWorkflow {

  private final ActivityOptions options =
      ActivityOptions.newBuilder()
          .setStartToCloseTimeout(Duration.ofSeconds(5))
          .build();

  private final TranslationActivities activities =
      Workflow.newActivityStub(TranslationActivities.class, options);

  @Override
  public TranslationWorkflowOutput sayHelloGoodbye(TranslationWorkflowInput input) {
    String name = input.getName();
    String languageCode = input.getLanguageCode();

    // TODO Create an instance of your Activity input class and pass
    // in the parameters from the "activities.translateTerm()"" call below (line 31)

    // TODO Replace "String" below with your Activity output class
    // TODO Replace the parameters in the call to "translateTerm" below with
    // the instance of your input class
    String helloResult = activities.translateTerm("hello", languageCode);

    // TODO Update the "helloResult" variable below to use the getter method
    // to retrieve the input from the class instance
    String helloMessage = helloResult + ", " + name;

    // TODO Create an instance of your Activity input class and pass
    // in the parameters from the "activities.translateTerm()"" call below (line 43)

    // TODO Replace "String" below with your Activity output class
    // TODO Replace the parameters in the call to "translateTerm" below with
    // the instance of your input class
    String goodbyeResult = activities.translateTerm("goodbye", languageCode);

    // TODO Update the "helloResult" variable below to use the getter method
    // to retrieve the input from the class instance
    String goodbyeMessage = goodbyeResult + ", " + name;

    return new TranslationWorkflowOutput(helloMessage, goodbyeMessage);
  }
}
