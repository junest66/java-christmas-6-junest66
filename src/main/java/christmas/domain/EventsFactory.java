package christmas.domain;

import christmas.domain.event.ChristmasDiscountEvent;
import christmas.domain.event.DayOfWeekDiscountEvent;
import christmas.domain.event.Event;
import christmas.domain.event.GiveawayEvent;
import christmas.domain.event.SpecialDiscountEvent;
import java.util.ArrayList;
import java.util.List;

public class EventsFactory {
    private Order order;

    public EventsFactory(Order order) {
        this.order = order;
    }

    public List<Event> createEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new ChristmasDiscountEvent(order));
        events.add(new DayOfWeekDiscountEvent(order));
        events.add(new SpecialDiscountEvent(order));
        events.add(new GiveawayEvent(order));
        return events;
    }
}
