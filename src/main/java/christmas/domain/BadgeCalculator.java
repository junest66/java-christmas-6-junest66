package christmas.domain;

import christmas.constants.Badge;
import christmas.constants.Values;

public class BadgeCalculator {
    public String calculateBadge(BenefitResult benefitResult) {
        int totalDiscountAmount = benefitResult.getTotalAllBenefitAmount() * -1;
        if (totalDiscountAmount >= Badge.SANTA.getRequirement()) {
            return Badge.SANTA.getName();
        }
        if (totalDiscountAmount >= Badge.TREE.getRequirement()) {
            return Badge.TREE.getName();
        }
        if (totalDiscountAmount >= Badge.STAR.getRequirement()) {
            return Badge.STAR.getName();
        }
        return Values.NOT_EXIST;
    }
}
