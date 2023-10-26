package pizzaworkflow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.temporal.failure.ActivityFailure;
import io.temporal.testing.TestActivityEnvironment;

import pizzaworkflow.exceptions.InvalidChargeAmountException;
import pizzaworkflow.model.Address;
import pizzaworkflow.model.Bill;
import pizzaworkflow.model.Distance;
import pizzaworkflow.model.OrderConfirmation;

public class PizzaActivitiesTest {

  private TestActivityEnvironment testEnvironment;

  @BeforeEach
  public void init() {
    testEnvironment = TestActivityEnvironment.newInstance();
  }

  @AfterEach
  public void destroy() {
    testEnvironment.close();
  }

  @Test
  public void testGetDistanceTwoLineAddress() {
    testEnvironment.registerActivitiesImplementations(new PizzaActivitiesImpl());
    PizzaActivities activity = testEnvironment.newActivityStub(PizzaActivities.class);
    Address address =
        new Address("701 Mission Street", "Apartment 9C", "San Fancisco", "CA", "94103");
    Distance distance = activity.getDistance(address);

    assertEquals(20, distance.getKilometers());
  }

  @Test
  public void testGetDistanceOneLineAddress() {
    testEnvironment.registerActivitiesImplementations(new PizzaActivitiesImpl());
    PizzaActivities activity = testEnvironment.newActivityStub(PizzaActivities.class);
    Address address = new Address("917 Delores Street", "", "San Fancisco", "CA", "94103");
    Distance distance = activity.getDistance(address);

    assertEquals(8, distance.getKilometers());
  }

  @Test
  public void testSendBillTypicalOrder() throws InvalidChargeAmountException {
    testEnvironment.registerActivitiesImplementations(new PizzaActivitiesImpl());
    PizzaActivities activity = testEnvironment.newActivityStub(PizzaActivities.class);
    Bill bill = new Bill(12983, "PI314", "2 large cheese pizzas", 2600);

    OrderConfirmation confirmation = activity.sendBill(bill);

    assertEquals("PI314", confirmation.getOrderNumber());
    assertEquals(2600, confirmation.getAmount());

  }

  // TODO: Write the testSendBillAppliesDiscount Test

  @Test
  public void testSendBillFailsWithNegativeAmount() throws InvalidChargeAmountException {
    testEnvironment.registerActivitiesImplementations(new PizzaActivitiesImpl());
    PizzaActivities activity = testEnvironment.newActivityStub(PizzaActivities.class);
    Bill bill = new Bill(21974, "QU812", "1 large supreme pizza", -1000);

    // Assert that an error was thrown and it was an Activity Failure
    Exception exception = assertThrows(ActivityFailure.class, () -> {
      OrderConfirmation confirmation = activity.sendBill(bill);
    });

    // Assert that the error has the expected message, which identifies
    // the invalid language code as the cause
    assertTrue(exception.getMessage().contains("invalid charge amount: -1000"),
        "expected error message");
  }
}
