package christmas.DTO;

import christmas.constants.MenuItem;
import christmas.domain.Order;
import java.util.Map;

public record OrderDTO(int date, Map<MenuItem, Integer> menu) {
    public OrderDTO(Order order) {
        this(order.getDate(), order.getMenu());
    }
}
