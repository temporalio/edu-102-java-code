package translationworkflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import translationworkflow.model.TranslationActivityInput;
import translationworkflow.model.TranslationActivityOutput;
import translationworkflow.model.TranslationWorkflowInput;
import translationworkflow.model.TranslationWorkflowOutput;

import java.time.Duration;

public class TranslationWorkflowImpl implements TranslationWorkflow {

    ActivityOptions options = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            .build();

    private final TranslationActivities activities = Workflow.newActivityStub(TranslationActivities.class, options);

    @Override
    public TranslationWorkflowOutput sayHelloGoodbye(TranslationWorkflowInput input) {
        String name = input.getName();
        String languageCode = input.getLanguageCode();

        TranslationActivityInput helloInput = new TranslationActivityInput("hello", languageCode);
        TranslationActivityOutput helloResult = activities.translateTerm(helloInput);
        String helloMessage = helloResult.getTranslation() + ", " + name;

        TranslationActivityInput goodbyeInput = new TranslationActivityInput("goodbye", languageCode);
        TranslationActivityOutput goodbyeResult = activities.translateTerm(goodbyeInput);
        String goodbyeMessage = goodbyeResult.getTranslation() + ", " + name;

        return new TranslationWorkflowOutput(helloMessage, goodbyeMessage);
    }
}
