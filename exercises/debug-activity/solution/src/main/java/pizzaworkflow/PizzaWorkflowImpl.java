package pizzaworkflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import io.temporal.failure.ApplicationFailure;

import pizzaworkflow.model.Address;
import pizzaworkflow.model.Bill;
import pizzaworkflow.model.Customer;
import pizzaworkflow.model.Distance;
import pizzaworkflow.model.OrderConfirmation;
import pizzaworkflow.model.Pizza;
import pizzaworkflow.model.PizzaOrder;
import pizzaworkflow.exceptions.InvalidChargeAmountException;
import pizzaworkflow.exceptions.OutOfServiceAreaException;

import java.time.Duration;
import java.util.List;

import org.slf4j.Logger;

public class PizzaWorkflowImpl implements PizzaWorkflow {

  public static final Logger logger = Workflow.getLogger(PizzaWorkflowImpl.class);

  ActivityOptions options =
      ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(5)).build();

  private final PizzaActivities activities =
      Workflow.newActivityStub(PizzaActivities.class, options);

  @Override
  public OrderConfirmation orderPizza(PizzaOrder order) {

    String orderNumber = order.getOrderNumber();
    Customer customer = order.getCustomer();
    List<Pizza> items = order.getItems();
    boolean isDelivery = order.isDelivery();
    Address address = order.getAddress();

    logger.info("orderPizza Workflow Invoked");

    int totalPrice = 0;
    for (Pizza pizza : items) {
      totalPrice += pizza.getPrice();
    }

    Distance distance;
    try {
      distance = activities.getDistance(address);
    } catch (NullPointerException e) {
      logger.error("Unable to get distance");
      throw new NullPointerException("Unable to get distance");
    }

    if (isDelivery && (distance.getKilometers() > 25)) {
      logger.error("Customer lives outside the service area");
      throw ApplicationFailure.newFailure("Customer lives outside the service area",
          OutOfServiceAreaException.class.getName());
    }

    logger.info("distance is {}", distance.getKilometers());

    // Use a short Timer duration here to simulate the passage of time
    // while avoiding delaying the exercise.
    Workflow.sleep(Duration.ofSeconds(3));

    Bill bill = new Bill(customer.getCustomerID(), orderNumber, "Pizza", totalPrice);

    OrderConfirmation confirmation;
    try {
      confirmation = activities.sendBill(bill);
    } catch (InvalidChargeAmountException e) {
      logger.error("Unable to bill customer");
      throw Workflow.wrap(new InvalidChargeAmountException("Unable to bill customer"));
    }

    return confirmation;
  }
}
