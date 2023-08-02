package translationworkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import translationworkflow.model.TranslationWorkflowInput;
import translationworkflow.model.TranslationWorkflowOutput;

public class Starter {

    public static void main(String[] args) throws Exception {

        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        WorkflowClient client = WorkflowClient.newInstance(service);

        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId("translation-workflow")
                .setTaskQueue("translation-tasks")
                .build();

        TranslationWorkflow workflow = client.newWorkflowStub(TranslationWorkflow.class, options);

        String name = args[0];
        String languageCode = args[1];

        TranslationWorkflowInput input = new TranslationWorkflowInput(name, languageCode);

        TranslationWorkflowOutput greeting = workflow.sayHelloGoodbye(input);

        System.out.printf("Workflow result: %s\n", greeting);
    }
}
