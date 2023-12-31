package ageestimationworkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

public class Starter {
  public static void main(String[] args) throws Exception {

    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

    WorkflowClient client = WorkflowClient.newInstance(service);

    String name = args[0];

    WorkflowOptions options = WorkflowOptions.newBuilder()
        .setWorkflowId("testing-estimate-age-example")
        .setTaskQueue(Constants.TASK_QUEUE_NAME)
        .build();

    AgeEstimationWorkflow workflow = client.newWorkflowStub(AgeEstimationWorkflow.class, options);

    String result = workflow.estimateAge(name);

    System.out.printf("Workflow result: %s\n", result);
    System.exit(0);
  }
}
