package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.EventType;
import christmas.constants.Values;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BenefitResultTest {
    BenefitResult benefitResult = new BenefitResult();

    @BeforeEach
    void setup() {
        benefitResult.addBenefit(EventType.CHRISTMAS.getDisplayName(), -1200);
        benefitResult.addBenefit(EventType.WEEKDAY.getDisplayName(), -4046);
        benefitResult.addBenefit(EventType.SPECIAL.getDisplayName(), -1000);
        benefitResult.addBenefit(EventType.GIVEAWAY.getDisplayName(), -25000);
    }

    @DisplayName("addBenefit 메서드가 혜택 정보를 정확히 추가한다")
    @Test
    void addDiscountShouldAddCorrectDiscountInfo() {
        String eventName = EventType.WEEKDAY.getDisplayName();
        int discountAmount = -4046;
        BenefitResult tempBenefitResult = new BenefitResult();
        tempBenefitResult.addBenefit(eventName, discountAmount);
        assertThat(tempBenefitResult.getBenefitResult()).containsEntry(eventName, discountAmount);
    }

    @DisplayName("전체 혜택금액을 계산한다")
    @Test
    void shouldCorrectlyCalculateTotalBenefitAmount() {
        int expectedValue = -31246;
        assertThat(benefitResult.getTotalAllBenefitAmount()).isEqualTo(expectedValue);
    }

    @DisplayName("전체 할인금액을 계산한다")
    @Test
    void shouldCorrectlyCalculateTotalDiscountAmount() {
        int expectedValue = -6246;
        assertThat(benefitResult.getTotalDiscountAmount()).isEqualTo(expectedValue);
    }

    @DisplayName("증정 이벤트의 혜택이 0이 아니면 증정메뉴는 '샴폐인 1개' 이다.")
    @Test
    void shouldReturnChampagneWhenGiveawayBenefitIsNotZero() {
        String expectedValue = Values.GIVEAWAY_EVENT_ITEMS;
        assertThat(benefitResult.getGiveawayMenu()).isEqualTo(expectedValue);
    }

    @DisplayName("증정 이벤트의 혜택이 0이면 증정메뉴는 '없음' 이다.")
    @Test
    void shouldReturnNoneWhenGiveawayBenefitIsZero() {
        benefitResult.addBenefit(EventType.GIVEAWAY.getDisplayName(), 0);
        String expectedValue = Values.NOT_EXIST;
        assertThat(benefitResult.getGiveawayMenu()).isEqualTo(expectedValue);
    }
}
