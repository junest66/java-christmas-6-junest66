package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.EventType;
import christmas.constants.MenuItem;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentAmountCalculatorTest {
    private Order order;
    private BenefitResult benefitResult = new BenefitResult();

    @BeforeEach
    void setup() {
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.TBONE_STEAK, 1);
        menuOrder.put(MenuItem.BBQ_RIBS, 1);
        menuOrder.put(MenuItem.CHOCO_CAKE, 2);
        menuOrder.put(MenuItem.ZERO_COLA, 1);
        order = new Order(3, menuOrder);
        benefitResult.addBenefit(EventType.CHRISTMAS.getDisplayName(), -1200);
        benefitResult.addBenefit(EventType.WEEKDAY.getDisplayName(), -4046);
        benefitResult.addBenefit(EventType.SPECIAL.getDisplayName(), -1000);
        benefitResult.addBenefit(EventType.GIVEAWAY.getDisplayName(), -25000);
    }

    @DisplayName("지불 금액을 계산한다")
    @Test
    void shouldCalculateCorrectPaymentAmount() {
        int expectedValue = 135754;
        PaymentAmountCalculator paymentAmountCalculator = new PaymentAmountCalculator();
        int paymentAmount = paymentAmountCalculator.calculatePaymentAmount(order, benefitResult);
        assertThat(paymentAmount).isEqualTo(expectedValue);
    }
}
