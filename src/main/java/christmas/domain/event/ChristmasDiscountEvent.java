package christmas.domain.event;

import christmas.constants.EventType;
import christmas.constants.Values;
import christmas.domain.Order;

public class ChristmasDiscountEvent implements Event {
    private final Order order;

    public ChristmasDiscountEvent(Order order) {
        this.order = order;
    }

    @Override
    public String getEventName() {
        return EventType.CHRISTMAS.getDisplayName();
    }

    @Override
    public int calculateBenefit() {
        if (isNotApplicable()) {
            return 0;
        }
        int startDiscount = Values.CHRISTMAS_EVENT_START_DISCOUNT;
        startDiscount += (order.getDate() - 1) * Values.CHRISTMAS_EVENT_DAILY_ADDITION;
        return startDiscount;
    }

    private boolean isNotApplicable() {
        if (order.calculateTotalPriceBeforeDiscount() < Values.MINIMUM_TOTAL_ORDER_AMOUNT_FOR_EVENT) {
            return true;
        }
        if (order.getDate() < Values.MINIMUM_DATE_FOR_EVENT
                || order.getDate() > Values.MAXIMUM_DATE_FOR_CHRISTMAS_EVENT) {
            return true;
        }
        return false;
    }
}
