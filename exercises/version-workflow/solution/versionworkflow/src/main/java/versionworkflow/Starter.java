package vesionworkflow;

import pizzaworkflow.Constants;
import translationworkflow.TranslationWorkflow;
import translationworkflow.model.TranslationWorkflowInput;
import translationworkflow.model.TranslationWorkflowOutput;

public class Starter {

        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        WorkflowClient client = WorkflowClient.newInstance(service);

        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId("translation-workflow")
                .setTaskQueue(Constants.taskQueueName)
                .build();

        LoanProcessingWorkflow workflow = client.newWorkflowStub(LoanProcessingWorkflow.class, options);

        String customerId = args[0];

        TranslationWorkflowInput input = new TranslationWorkflowInput(name, languageCode);

        TranslationWorkflowOutput greeting = workflow.sayHelloGoodbye(input);

        System.out.printf("Workflow result: %s\n", greeting);
        System.exit(0);
}
