package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.MenuItem;
import christmas.domain.Order;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChristmasDiscountEventTest {
    @DisplayName("총 주문 금액이 만원이 안될시 혜택금액은 0원이다.")
    @Test
    void noDiscountForLowTotalOrder() {
        int date = 5;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.ZERO_COLA, 1);
        menuOrder.put(MenuItem.TAPAS, 1);
        Order order = new Order(date, menuOrder);
        ChristmasDiscountEvent christmasDiscountEvent = new ChristmasDiscountEvent(order);
        assertThat(christmasDiscountEvent.calculateBenefit()).isEqualTo(0);
    }

    @DisplayName("날짜가 1일과 25일사이가 아니라면 혜택금액은 0원이다.")
    @Test
    void noDiscountOutsideDiscountPeriod() {
        int date = 26;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.ZERO_COLA, 1);
        menuOrder.put(MenuItem.TAPAS, 1);
        menuOrder.put(MenuItem.SEAFOOD_PASTA, 2);
        Order order = new Order(date, menuOrder);
        ChristmasDiscountEvent christmasDiscountEvent = new ChristmasDiscountEvent(order);
        assertThat(christmasDiscountEvent.calculateBenefit()).isEqualTo(0);
    }

    @DisplayName("3일이므로 1200원 크리스마스 디데이 할인을 한다.")
    @Test
    void discountForTwentyFifthDayOrder() {
        int expectedValue = 1200;
        int date = 3;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.TBONE_STEAK, 1);
        menuOrder.put(MenuItem.BBQ_RIBS, 1);
        menuOrder.put(MenuItem.CHOCO_CAKE, 2);
        menuOrder.put(MenuItem.ZERO_COLA, 2);
        Order order = new Order(date, menuOrder);
        ChristmasDiscountEvent christmasDiscountEvent = new ChristmasDiscountEvent(order);
        assertThat(christmasDiscountEvent.calculateBenefit()).isEqualTo(expectedValue);
    }

    @DisplayName("25일이므로 3400원 크리스마스 디데이 할인을 한다.")
    @Test
    void asd4() {
        int expectedValue = 3400;
        int date = 25;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.TBONE_STEAK, 1);
        menuOrder.put(MenuItem.BBQ_RIBS, 1);
        menuOrder.put(MenuItem.CHOCO_CAKE, 2);
        menuOrder.put(MenuItem.ZERO_COLA, 2);
        Order order = new Order(date, menuOrder);
        ChristmasDiscountEvent christmasDiscountEvent = new ChristmasDiscountEvent(order);
        assertThat(christmasDiscountEvent.calculateBenefit()).isEqualTo(expectedValue);
    }
}
