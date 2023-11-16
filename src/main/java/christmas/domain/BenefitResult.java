package christmas.domain;

import christmas.constants.EventType;
import christmas.constants.Values;
import java.util.LinkedHashMap;
import java.util.Map;

public class BenefitResult {
    private Map<String, Integer> benefitResult;

    public BenefitResult() {
        this.benefitResult = new LinkedHashMap<>();
    }

    public void addBenefit(String eventName, int benefitAmount) {
        benefitResult.put(eventName, benefitAmount);
    }

    public int getTotalAllBenefitAmount() {
        return benefitResult.values().stream().mapToInt(Integer::intValue).sum();
    }

    public int getTotalDiscountAmount() {
        return benefitResult.entrySet().stream()
                .filter(entry -> !entry.getKey().equals(EventType.GIVEAWAY.getDisplayName()))
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public String getGiveawayMenu() {
        int giveawayBenefitAmount = benefitResult.get(EventType.GIVEAWAY.getDisplayName());
        if (giveawayBenefitAmount == 0) {
            return Values.NOT_EXIST;
        }
        return Values.GIVEAWAY_EVENT_ITEMS;
    }

    public Map<String, Integer> getBenefitResult() {
        return benefitResult;
    }
}
