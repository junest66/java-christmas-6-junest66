package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.MenuItem;
import christmas.constants.Values;
import christmas.domain.Order;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DayOfWeekDiscountEventTest {
    @DisplayName("주말이면 적용 되는 이벤트 이름은 '주말 할인'이다")
    @Test
    void verifyWeekendEventName() {
        String expectedValue = Values.WEEKEND_EVENT_NAME;
        int date = 2;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.제로콜라, 1);
        menuOrder.put(MenuItem.타파스, 1);
        Order order = new Order(date, menuOrder);
        DayOfWeekDiscountEvent dayOfWeekDiscountEvent = new DayOfWeekDiscountEvent(order);
        assertThat(dayOfWeekDiscountEvent.getEventName()).isEqualTo(expectedValue);
    }

    @DisplayName("평일이면 적용 되는 이벤트 이름은 '평일 할인'이다")
    @Test
    void verifyWeekdayEventName() {
        String expectedValue = Values.WEEKDAY_EVENT_NAME;
        int date = 5;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.제로콜라, 1);
        menuOrder.put(MenuItem.타파스, 1);
        Order order = new Order(date, menuOrder);
        DayOfWeekDiscountEvent dayOfWeekDiscountEvent = new DayOfWeekDiscountEvent(order);
        assertThat(dayOfWeekDiscountEvent.getEventName()).isEqualTo(expectedValue);
    }

    @DisplayName("총 주문 금액이 만원이 안될시 할인금액은 0원이다.")
    @Test
    void noDiscountForLowTotalOrder() {
        int date = 5;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.제로콜라, 1);
        menuOrder.put(MenuItem.타파스, 1);
        Order order = new Order(date, menuOrder);
        ChristmasDiscountEvent christmasDiscountEvent = new ChristmasDiscountEvent(order);
        assertThat(christmasDiscountEvent.calculateDiscount()).isEqualTo(0);
    }

    @DisplayName("날짜가 1일과 31일사이가 아니라면 할인금액은 0원이다.")
    @Test
    void noDiscountOutsideDiscountPeriod() {
        int date = 32;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.제로콜라, 1);
        menuOrder.put(MenuItem.타파스, 1);
        menuOrder.put(MenuItem.해산물파스타, 2);
        Order order = new Order(date, menuOrder);
        ChristmasDiscountEvent christmasDiscountEvent = new ChristmasDiscountEvent(order);
        assertThat(christmasDiscountEvent.calculateDiscount()).isEqualTo(0);
    }

    @DisplayName("평일 이벤트 적용 시 디저트 메뉴 2개 주문시 4046원 할인된다.")
    @Test
    void weekdayDessertDiscount() {
        int expectedValue = 4046;
        int date = 5;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.제로콜라, 1);
        menuOrder.put(MenuItem.타파스, 1);
        menuOrder.put(MenuItem.초코케이크, 1);
        menuOrder.put(MenuItem.아이스크림, 1);
        Order order = new Order(date, menuOrder);
        DayOfWeekDiscountEvent dayOfWeekDiscountEvent = new DayOfWeekDiscountEvent(order);
        assertThat(dayOfWeekDiscountEvent.calculateDiscount()).isEqualTo(expectedValue);
    }

    @DisplayName("평일 이벤트 적용 시 디저트 메뉴 0개 주문시 0원 할인된다.")
    @Test
    void noWeekdayDiscountWithoutDessert() {
        int expectedValue = 0;
        int date = 5;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.제로콜라, 1);
        menuOrder.put(MenuItem.타파스, 1);
        Order order = new Order(date, menuOrder);
        DayOfWeekDiscountEvent dayOfWeekDiscountEvent = new DayOfWeekDiscountEvent(order);
        assertThat(dayOfWeekDiscountEvent.calculateDiscount()).isEqualTo(expectedValue);
    }

    @DisplayName("주말 이벤트 적용 시 메인 메뉴 2개 주문시 4046원 할인된다.")
    @Test
    void weekendMainCourseDiscount() {
        int expectedValue = 4046;
        int date = 1;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.제로콜라, 1);
        menuOrder.put(MenuItem.타파스, 1);
        menuOrder.put(MenuItem.티본스테이크, 1);
        menuOrder.put(MenuItem.바비큐립, 1);
        Order order = new Order(date, menuOrder);
        DayOfWeekDiscountEvent dayOfWeekDiscountEvent = new DayOfWeekDiscountEvent(order);
        assertThat(dayOfWeekDiscountEvent.calculateDiscount()).isEqualTo(expectedValue);
    }

    @DisplayName("주말 이벤트 적용 시 메인 메뉴 0개 주문시 0원 할인된다.")
    @Test
    void noWeekendDiscountWithoutMainCourse() {
        int expectedValue = 0;
        int date = 2;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.제로콜라, 1);
        menuOrder.put(MenuItem.타파스, 1);
        Order order = new Order(date, menuOrder);
        DayOfWeekDiscountEvent dayOfWeekDiscountEvent = new DayOfWeekDiscountEvent(order);
        assertThat(dayOfWeekDiscountEvent.calculateDiscount()).isEqualTo(expectedValue);
    }

}
