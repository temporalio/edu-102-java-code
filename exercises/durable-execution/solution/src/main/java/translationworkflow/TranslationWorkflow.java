package translationworkflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import translationworkflow.model.TranslationWorkflowInput;
import translationworkflow.model.TranslationWorkflowOutput;

@WorkflowInterface
public interface TranslationWorkflow {

    @WorkflowMethod
    TranslationWorkflowOutput sayHelloGoodbye(TranslationWorkflowInput input);

}
