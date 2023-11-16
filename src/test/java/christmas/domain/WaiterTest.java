package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import christmas.constants.MenuItem;
import christmas.constants.Messages;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WaiterTest {

    @DisplayName("주문에 없는 메뉴가 있으면 예외를 발생한다")
    @Test
    void shouldThrowExceptionForInvalidMenuItems() {
        int date = 5;
        Map<String, Integer> menuOrderHistory = new HashMap<>();
        menuOrderHistory.put("해물파스타", 5); // 없는 메뉴
        menuOrderHistory.put("제로콜라", 2);
        menuOrderHistory.put("타파스", 1);
        Waiter waiter = new Waiter();
        assertThatThrownBy(() -> waiter.takeOrder(date, menuOrderHistory))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Messages.INVALID_ORDER_ERROR_MESSAGE);
    }

    @DisplayName("정상 주문에 대해 예외가 발생하지 않는다.")
    @Test
    void shouldNotThrowExceptionForValidOrder() {
        int date = 5;
        Map<String, Integer> menuOrderHistory = new HashMap<>();
        menuOrderHistory.put("해산물파스타", 5);
        menuOrderHistory.put("제로콜라", 2);
        menuOrderHistory.put("타파스", 1);
        Waiter waiter = new Waiter();
        assertDoesNotThrow(() -> waiter.takeOrder(date, menuOrderHistory));
    }

    @DisplayName("정상 주문에 대해 주문이 생성된다.")
    @Test
    void shouldCreateValidOrderForGivenOrderDetails() {
        int date = 5;
        Map<String, Integer> menuOrderHistory = new HashMap<>();
        menuOrderHistory.put("해산물파스타", 5);
        menuOrderHistory.put("제로콜라", 2);
        menuOrderHistory.put("타파스", 1);
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.SEAFOOD_PASTA, 5);
        menuOrder.put(MenuItem.ZERO_COLA, 2);
        menuOrder.put(MenuItem.TAPAS, 1);
        Waiter waiter = new Waiter();
        Order order = waiter.takeOrder(date, menuOrderHistory);
        assertThat(order).isEqualToComparingFieldByField(new Order(5, menuOrder));
    }
}
