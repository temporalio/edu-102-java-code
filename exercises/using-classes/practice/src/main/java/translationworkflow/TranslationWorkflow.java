package translationworkflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import translationworkflow.model.TranslationWorkflowInput;
import translationworkflow.model.TranslationWorkflowOutput;

@WorkflowInterface
public interface TranslationWorkflow {

  @WorkflowMethod
  // TODO: Update the method declaration to take a single parameter
  // of type TranslationWorkflowInput as input and to return an 
  // object of type TranslationWorkflowOutput as output
  String sayHelloGoodbye(String term, String languageCode);

}
