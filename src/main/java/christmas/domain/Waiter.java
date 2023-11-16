package christmas.domain;

import christmas.constants.MenuItem;
import christmas.constants.Messages;
import java.util.Map;
import java.util.stream.Collectors;

public class Waiter {

    public Order takeOrder(int date, Map<String, Integer> orderHistory) {
        validateOrder(orderHistory);
        return createOrder(date, orderHistory);
    }

    private void validateOrder(Map<String, Integer> orderHistory) {
        if (MenuItem.isOrderInvalid(orderHistory)) {
            throw new IllegalArgumentException(Messages.INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private Order createOrder(int date, Map<String, Integer> orderHistory) {
        Map<MenuItem, Integer> mappedOrder = orderHistory.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> MenuItem.findByKoreanName(e.getKey())
                                .orElseThrow(() -> new IllegalArgumentException(Messages.INVALID_ORDER_ERROR_MESSAGE)),
                        Map.Entry::getValue
                ));
        return new Order(date, mappedOrder);
    }
}
