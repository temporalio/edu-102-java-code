package pizzaworkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import pizzaworkflow.model.PizzaOrder;
import pizzaworkflow.model.Pizza;
import pizzaworkflow.model.Customer;
import pizzaworkflow.model.OrderConfirmation;
import pizzaworkflow.model.Distance;
import pizzaworkflow.model.Address;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Starter {
    public static void main(String[] args) throws Exception {

        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        WorkflowClient client = WorkflowClient.newInstance(service);

        PizzaOrder order = createPizzaOrder();

        String workflowID = String.format("pizza-workflow-order-%s", order.getOrderNumber());

        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId(workflowID)
                .setTaskQueue(Constants.taskQueueName)
                .build();

        PizzaWorkflow workflow = client.newWorkflowStub(PizzaWorkflow.class, options);

        OrderConfirmation orderConfirmation = workflow.orderPizza(order);

        System.out.printf("Workflow result: %s\n", orderConfirmation);
        System.exit(0);
    }

    private static PizzaOrder createPizzaOrder() {
        Customer customer = new Customer(8675309, "Lisa Anderson", "lisa@example.com", "555-555-0000");
        Address address = new Address("742 Evergreen Terrace", "Apartment 221B",
                "Albuquerque", "NM", "87101");
        Pizza pizza1 = new Pizza("Large, with mushrooms and onions", 1500);
        Pizza pizza2 = new Pizza("Small, with pepperoni", 1200);
        // TODO: Add a new pizza object here
        
        List<Pizza> orderList = new ArrayList<Pizza>(Arrays.asList(pizza1, pizza2)); // TODO: Add the pizza to the list

        PizzaOrder order = new PizzaOrder("XD001", customer, orderList, true, address);

        return order;

    }
}
