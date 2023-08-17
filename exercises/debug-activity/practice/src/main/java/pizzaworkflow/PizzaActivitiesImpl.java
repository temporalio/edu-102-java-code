package pizzaworkflow;

import pizzaworkflow.model.OrderConfirmation;
import pizzaworkflow.model.Address;
import pizzaworkflow.model.Distance;
import pizzaworkflow.model.Bill;

import pizzaworkflow.exceptions.InvalidChargeAmountException;

import java.time.Instant;

import io.temporal.activity.Activity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PizzaActivitiesImpl implements PizzaActivities {

  private static final Logger logger = LoggerFactory.getLogger(PizzaActivitiesImpl.class);

  @Override
  public Distance getDistance(Address address) {

    logger.info("getDistance invoked; determining distance to customer address");

    // this is a simulation, which calculates a fake (but consistent)
    // distance for a customer address based on its length. The value
    // will therefore be different when called with different addresses,
    // but will be the same across all invocations with the same address.

    int kilometers = address.getLine1().length() + address.getLine2().length() - 10;
    if (kilometers < 1) {
      kilometers = 5;
    }

    Distance distance = new Distance(kilometers);

    logger.info("getDistance complete: {}", distance.getKilometers());
    return distance;
  }

  @Override
  public OrderConfirmation sendBill(Bill bill) {
    int amount = bill.getAmount();

    logger.info("sendBill invoked: customer: {} amount: {}", bill.getCustomerID(), amount);

    int chargeAmount = amount;

    // This month's special offer: Get $5 off all orders over $30
    if (amount > 3000) {
      logger.info("Applying discount");

      chargeAmount = -500; // reduce amount charged by 500 cents
    }

    // reject invalid amounts before calling the payment processor
    if (chargeAmount < 0) {
      logger.error("invalid charge amount: {%d} (must be above zero)", chargeAmount);
      String errorMessage = "invalid charge amount: " + chargeAmount;
      throw Activity.wrap(new InvalidChargeAmountException(errorMessage));
    }

    // pretend we called a payment processing service here
    OrderConfirmation confirmation = new OrderConfirmation(bill.getOrderNumber(), "SUCCESS",
        "P24601", Instant.now().getEpochSecond(), chargeAmount);

    logger.debug("Sendbill complete: Confirmation Number: {}",
        confirmation.getConfirmationNumber());

    return confirmation;
  }
}
