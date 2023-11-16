package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.MenuItem;
import christmas.domain.Order;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GiveawayEventTest {
    @DisplayName("총 주문 금액이 12000원 미만일시 증정이벤트의 혜택금액은 0원이다.")
    @Test
    void noGiveawayForOrderBelowTwelveThousand() {
        int date = 5;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.ZERO_COLA, 2);
        menuOrder.put(MenuItem.TAPAS, 1);
        Order order = new Order(date, menuOrder);
        GiveawayEvent giveawayEvent = new GiveawayEvent(order);
        assertThat(giveawayEvent.calculateBenefit()).isEqualTo(0);
    }

    @DisplayName("날짜가 1일과 31일사이가 아니라면 증정이벤트의 혜택금액 0원이다.")
    @Test
    void noGiveawayOutsideEligibleDates() {
        int date = 32;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.ZERO_COLA, 1);
        menuOrder.put(MenuItem.TAPAS, 1);
        menuOrder.put(MenuItem.SEAFOOD_PASTA, 2);
        Order order = new Order(date, menuOrder);
        GiveawayEvent giveawayEvent = new GiveawayEvent(order);
        assertThat(giveawayEvent.calculateBenefit()).isEqualTo(0);
    }

    @DisplayName("증정 이벤트 조건이 충족되면 혜택 금액은 샴페인 1개 가격인 25000원이다.")
    @Test
    void verifyBenefitAmountForEligibleGiveawayEvent() {
        int expectedValue = MenuItem.CHAMPAGNE.getPrice();
        int date = 5;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.ZERO_COLA, 2);
        menuOrder.put(MenuItem.TAPAS, 2);
        menuOrder.put(MenuItem.TBONE_STEAK, 2);
        Order order = new Order(date, menuOrder);
        GiveawayEvent giveawayEvent = new GiveawayEvent(order);
        assertThat(giveawayEvent.calculateBenefit()).isEqualTo(expectedValue);
    }
}
