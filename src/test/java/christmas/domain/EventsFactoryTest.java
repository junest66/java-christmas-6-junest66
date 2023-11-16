package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.MenuItem;
import christmas.domain.event.ChristmasDiscountEvent;
import christmas.domain.event.DayOfWeekDiscountEvent;
import christmas.domain.event.Event;
import christmas.domain.event.GiveawayEvent;
import christmas.domain.event.SpecialDiscountEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EventsFactoryTest {
    @DisplayName("이벤트 리스트를 잘 생성한다")
    @Test
    void testCreateEvents() {
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.SEAFOOD_PASTA, 5);
        menuOrder.put(MenuItem.ZERO_COLA, 2);
        Order order = new Order(3, menuOrder);
        EventsFactory eventsFactory = new EventsFactory(order);
        List<Event> events = eventsFactory.createEvents();
        assertThat(events).hasSize(4);
        assertThat(events.get(0)).isInstanceOf(ChristmasDiscountEvent.class);
        assertThat(events.get(1)).isInstanceOf(DayOfWeekDiscountEvent.class);
        assertThat(events.get(2)).isInstanceOf(SpecialDiscountEvent.class);
        assertThat(events.get(3)).isInstanceOf(GiveawayEvent.class);
    }
}
