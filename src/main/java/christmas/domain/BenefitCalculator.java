package christmas.domain;

import christmas.domain.event.Event;
import java.util.List;

public class BenefitCalculator {
    private List<Event> events;

    public BenefitCalculator(List<Event> events) {
        this.events = events;
    }

    public BenefitResult calculateBenefit() {
        BenefitResult benefitResult = new BenefitResult();
        for (Event event : events) {
            String eventName = event.getEventName();
            int benefitAmount = event.calculateBenefit() * -1;
            benefitResult.addBenefit(eventName, benefitAmount);
        }
        return benefitResult;
    }
}
