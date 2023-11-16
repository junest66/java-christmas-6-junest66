package christmas.domain.event;

import christmas.constants.EventType;
import christmas.constants.Values;
import christmas.domain.Order;

public class SpecialDiscountEvent implements Event {
    private final Order order;

    public SpecialDiscountEvent(Order order) {
        this.order = order;
    }

    @Override
    public String getEventName() {
        return EventType.SPECIAL.getDisplayName();
    }

    @Override
    public int calculateBenefit() {
        if (isNotApplicable()) {
            return 0;
        }
        return Values.SPECIAL_EVENT_DISCOUNT_AMOUNT;
    }

    private boolean isNotApplicable() {
        if (order.calculateTotalPriceBeforeDiscount() < Values.MINIMUM_TOTAL_ORDER_AMOUNT_FOR_EVENT) {
            return true;
        }
        if (order.getDate() < Values.MINIMUM_DATE_FOR_EVENT || order.getDate() > Values.MAXIMUM_DATE_FOR_EVENT) {
            return true;
        }
        if (!Values.starDay.contains(order.getDate())) {
            return true;
        }
        return false;
    }
}
