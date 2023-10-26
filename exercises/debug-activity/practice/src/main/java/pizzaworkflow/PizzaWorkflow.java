package pizzaworkflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import pizzaworkflow.model.PizzaOrder;
import pizzaworkflow.model.OrderConfirmation;

@WorkflowInterface
public interface PizzaWorkflow {

  @WorkflowMethod
  OrderConfirmation orderPizza(PizzaOrder order);

}
