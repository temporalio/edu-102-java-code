package pizzaworkflow;

import io.temporal.activity.ActivityInterface;
import pizzaworkflow.model.Distance;
import pizzaworkflow.exceptions.InvalidChargeAmountException;
import pizzaworkflow.model.Address;
import pizzaworkflow.model.OrderConfirmation;
import pizzaworkflow.model.Bill;

@ActivityInterface
public interface PizzaActivities {

  Distance getDistance(Address address);

  OrderConfirmation sendBill(Bill bill) throws InvalidChargeAmountException;
}
