package christmas.DTO;

import christmas.domain.BenefitResult;
import java.util.Map;

public record BenefitResultDTO(Map<String, Integer> benefitResult) {
    public BenefitResultDTO(BenefitResult benefitResult) {
        this(benefitResult.getBenefitResult());
    }

    public boolean isAllZeroValues() {
        return benefitResult.values().stream().allMatch(value -> value == 0);
    }
}
