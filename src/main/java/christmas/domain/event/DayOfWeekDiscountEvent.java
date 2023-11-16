package christmas.domain.event;

import christmas.constants.EventType;
import christmas.constants.MenuCategory;
import christmas.constants.Values;
import christmas.domain.Order;

public class DayOfWeekDiscountEvent implements Event {
    private final Order order;

    public DayOfWeekDiscountEvent(Order order) {
        this.order = order;
    }

    @Override
    public String getEventName() {
        if (isWeekend(order)) {
            return EventType.WEEKEND.getDisplayName();
        }
        return EventType.WEEKDAY.getDisplayName();
    }

    @Override
    public int calculateBenefit() {
        if (isNotApplicable()) {
            return 0;
        }
        if (isWeekend(order)) {
            int dessertCount = order.getMenuCategoryCount(MenuCategory.MAIN);
            return dessertCount * Values.WEEKEND_DISCOUNT_AMOUNT;
        }
        int dessertCount = order.getMenuCategoryCount(MenuCategory.DESSERT);
        return dessertCount * Values.WEEKDAY_DISCOUNT_AMOUNT;
    }

    private boolean isNotApplicable() {
        if (order.calculateTotalPriceBeforeDiscount() < Values.MINIMUM_TOTAL_ORDER_AMOUNT_FOR_EVENT) {
            return true;
        }
        if (order.getDate() < Values.MINIMUM_DATE_FOR_EVENT || order.getDate() > Values.MAXIMUM_DATE_FOR_EVENT) {
            return true;
        }
        return false;
    }


    private boolean isWeekend(Order order) {
        int dayOfWeek = order.getDate() % Values.DAYS_IN_WEEK;
        return dayOfWeek == Values.WEEKEND_DAY_FRIDAY || dayOfWeek == Values.WEEKEND_DAY_SATURDAY;
    }
}
