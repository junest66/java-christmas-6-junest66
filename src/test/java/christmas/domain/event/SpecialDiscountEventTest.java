package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.MenuItem;
import christmas.constants.Values;
import christmas.domain.Order;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SpecialDiscountEventTest {
    @DisplayName("총 주문 금액이 만원이 안될시 혜택금액은 0원이다.")
    @Test
    void noDiscountForLowTotalOrder() {
        int date = 5;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.ZERO_COLA, 1);
        menuOrder.put(MenuItem.TAPAS, 1);
        Order order = new Order(date, menuOrder);
        SpecialDiscountEvent specialDiscountEvent = new SpecialDiscountEvent(order);
        assertThat(specialDiscountEvent.calculateBenefit()).isEqualTo(0);
    }

    @DisplayName("날짜가 1일과 31일사이가 아니라면 혜택금액은 0원이다.")
    @Test
    void noDiscountOutsideDiscountPeriod() {
        int date = 32;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.ZERO_COLA, 1);
        menuOrder.put(MenuItem.TAPAS, 1);
        menuOrder.put(MenuItem.SEAFOOD_PASTA, 2);
        Order order = new Order(date, menuOrder);
        SpecialDiscountEvent specialDiscountEvent = new SpecialDiscountEvent(order);
        assertThat(specialDiscountEvent.calculateBenefit()).isEqualTo(0);
    }

    @DisplayName("날짜가 이벤트 달력에 없으면 혜택금액은 0원이다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 5, 30})
    void noSpecialDiscountWhenDateNotInEventCalendar(int date) {
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.ZERO_COLA, 1);
        menuOrder.put(MenuItem.TAPAS, 1);
        menuOrder.put(MenuItem.SEAFOOD_PASTA, 2);
        Order order = new Order(date, menuOrder);
        SpecialDiscountEvent specialDiscountEvent = new SpecialDiscountEvent(order);
        assertThat(specialDiscountEvent.calculateBenefit()).isEqualTo(0);
    }

    @DisplayName("날짜가 이벤트 달력에 있으면 특별할인이 적용된다.")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void applySpecialDiscountWhenDateInEventCalendar(int date) {
        int expectedValue = Values.SPECIAL_EVENT_DISCOUNT_AMOUNT;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.ZERO_COLA, 1);
        menuOrder.put(MenuItem.TAPAS, 1);
        menuOrder.put(MenuItem.SEAFOOD_PASTA, 2);
        Order order = new Order(date, menuOrder);
        SpecialDiscountEvent specialDiscountEvent = new SpecialDiscountEvent(order);
        assertThat(specialDiscountEvent.calculateBenefit()).isEqualTo(expectedValue);
    }
}
