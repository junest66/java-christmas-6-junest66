package christmas.DTO;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.BenefitResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitResultDTOTest {
    @DisplayName("모든 혜택 금액이 0이면 true를 반환한다.")
    @Test
    void shouldReturnTrueWhenAllBenefitsAreZero() {
        BenefitResult benefitResult = new BenefitResult();
        benefitResult.addBenefit("크리스마스 디데이 할인", 0);
        benefitResult.addBenefit("평일 할인", 0);
        benefitResult.addBenefit("증정 이벤트", 0);
        benefitResult.addBenefit("특별 할인", 0);
        BenefitResultDTO benefitResultDTO = new BenefitResultDTO(benefitResult);
        assertThat(benefitResultDTO.isAllZeroValues()).isTrue();
    }

    @DisplayName("하나라도 혜택 금액이 0이 아니라면 false를 반환한다.")
    @Test
    void shouldReturnFalseWhenAnyBenefitIsNonZero() {
        BenefitResult benefitResult = new BenefitResult();
        benefitResult.addBenefit("크리스마스 디데이 할인", 0);
        benefitResult.addBenefit("평일 할인", 1000);
        benefitResult.addBenefit("증정 이벤트", 1000);
        benefitResult.addBenefit("특별 할인", 0);
        BenefitResultDTO benefitResultDTO = new BenefitResultDTO(benefitResult);
        assertThat(benefitResultDTO.isAllZeroValues()).isFalse();
    }
}
