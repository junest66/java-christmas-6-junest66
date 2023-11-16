package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.EventType;
import christmas.constants.MenuItem;
import christmas.domain.event.ChristmasDiscountEvent;
import christmas.domain.event.DayOfWeekDiscountEvent;
import christmas.domain.event.Event;
import christmas.domain.event.GiveawayEvent;
import christmas.domain.event.SpecialDiscountEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitCalculatorTest {
    private Order order;
    private List<Event> events;

    @BeforeEach
    void setUp() {
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.TBONE_STEAK, 1);
        menuOrder.put(MenuItem.BBQ_RIBS, 1);
        menuOrder.put(MenuItem.CHOCO_CAKE, 2);
        menuOrder.put(MenuItem.ZERO_COLA, 1);
        order = new Order(3, menuOrder);
        events = Arrays.asList(new ChristmasDiscountEvent(order), new DayOfWeekDiscountEvent(order),
                new SpecialDiscountEvent(order), new GiveawayEvent(order));
    }

    @DisplayName("할인 정보를 계산한다")
    @Test
    void shouldCalculateTotalDiscountsCorrectly() {
        BenefitCalculator benefitCalculator = new BenefitCalculator(events);
        BenefitResult expectedResult = new BenefitResult();
        expectedResult.addBenefit(EventType.CHRISTMAS.getDisplayName(), -1200);
        expectedResult.addBenefit(EventType.WEEKDAY.getDisplayName(), -4046);
        expectedResult.addBenefit(EventType.SPECIAL.getDisplayName(), -1000);
        expectedResult.addBenefit(EventType.GIVEAWAY.getDisplayName(), -25000);
        assertThat(benefitCalculator.calculateBenefit()).isEqualToComparingFieldByField(expectedResult);
    }
}
