package pizzaworkflow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.Transient;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.testing.TestWorkflowExtension;
import io.temporal.worker.Worker;
import io.temporal.client.WorkflowFailedException;
import io.temporal.worker.WorkflowImplementationOptions;

import pizzaworkflow.exceptions.InvalidChargeAmountException;
import pizzaworkflow.exceptions.OutOfServiceAreaException;
import pizzaworkflow.model.Address;
import pizzaworkflow.model.Bill;
import pizzaworkflow.model.Customer;
import pizzaworkflow.model.Distance;
import pizzaworkflow.model.OrderConfirmation;
import pizzaworkflow.model.Pizza;
import pizzaworkflow.model.PizzaOrder;

import java.lang.Exception;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

public class PizzaWorkflowTest {

  @RegisterExtension
  public static final TestWorkflowExtension testWorkflowExtension = TestWorkflowExtension
      .newBuilder().setWorkflowTypes(PizzaWorkflowImpl.class).setDoNotStart(true).build();

  @Test
  public void testSuccessfulPizzaOrder(TestWorkflowEnvironment testEnv, Worker worker,
      PizzaWorkflow workflow) throws InvalidChargeAmountException, OutOfServiceAreaException {

    PizzaOrder order = createPizzaOrderForTest();
    OrderConfirmation confirmation = new OrderConfirmation(order.getOrderNumber(), "SUCCESS",
        "P24601", Instant.now().getEpochSecond(), 2500);

    PizzaActivities mockedActivities =
        mock(PizzaActivities.class, withSettings().withoutAnnotations());

    when(mockedActivities.getDistance(any(Address.class))).thenReturn(new Distance(10));
    when(mockedActivities.sendBill(any(Bill.class))).thenReturn(confirmation);

    worker.registerActivitiesImplementations(mockedActivities);
    testEnv.start();

    OrderConfirmation result = workflow.orderPizza(order);

    assertEquals("XD001", result.getOrderNumber());
    assertEquals("SUCCESS", result.getStatus());
    assertEquals("P24601", result.getConfirmationNumber());
    assertEquals(2500, result.getAmount());
    assertNotEquals("", result.getBillingTimestamp());

  }

  @Test
  public void testFailedPizzaOrderCustomerOutsideDeliveryArea(TestWorkflowEnvironment testEnv,
      Worker worker, PizzaWorkflow workflow)
      throws InvalidChargeAmountException, OutOfServiceAreaException {

    PizzaOrder order = createPizzaOrderForTest();
    OrderConfirmation confirmation = new OrderConfirmation(order.getOrderNumber(), "SUCCESS",
        "AB9923", Instant.now().getEpochSecond(), 2500);

    PizzaActivities mockedActivities =
        mock(PizzaActivities.class, withSettings().withoutAnnotations());

    when(mockedActivities.getDistance(any(Address.class))).thenReturn(new Distance(30));

    // NOTE there is no Mock for the sendBill Activity because it won't be called,
    // given that the Workflow returns an error due to the distance

    worker.registerActivitiesImplementations(mockedActivities);
    testEnv.start();

    Exception exception = assertThrows(WorkflowFailedException.class, () -> {
      workflow.orderPizza(order);
    }, "OutOfServiceAreaException");

  }

  private static PizzaOrder createPizzaOrderForTest() {
    Customer customer = new Customer(12983, "Lisa Anderson", "lisa@example.com", "555-555-0000");
    Address address =
        new Address("742 Evergreen Terrace", "Apartment 221B", "Albuquerque", "NM", "87101");
    Pizza pizza1 = new Pizza("Large, with pepperoni", 1500);
    Pizza pizza2 = new Pizza("Small, with mushrooms and onions", 1000);

    List<Pizza> orderList = new ArrayList<Pizza>(Arrays.asList(pizza1, pizza2));

    PizzaOrder order = new PizzaOrder("XD001", customer, orderList, true, address);

    return order;

  }
}
