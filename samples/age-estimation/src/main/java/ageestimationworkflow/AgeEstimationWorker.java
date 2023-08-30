package ageestimationworkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class AgeEstimationWorker {
  public static void main(String[] args) {

    WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    WorkflowClient client = WorkflowClient.newInstance(service);
    WorkerFactory factory = WorkerFactory.newInstance(client);

    Worker worker = factory.newWorker(Constants.taskQueueName);

    worker.registerWorkflowImplementationTypes(AgeEstimationWorkflowImpl.class);

    worker.registerActivitiesImplementations(new AgeEstimationActivitiesImpl());

    factory.start();
  }
}
