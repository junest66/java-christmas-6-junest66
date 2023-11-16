package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.DTO.BenefitResultDTO;
import christmas.constants.MenuItem;
import christmas.constants.Messages;
import christmas.domain.BenefitCalculator;
import christmas.domain.BenefitResult;
import christmas.domain.EventsFactory;
import christmas.domain.Order;
import christmas.domain.event.Event;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputViewTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final OutputView outputView = new OutputView();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    private BenefitResultDTO prepareDiscountResultDTO(Map<MenuItem, Integer> menuOrder, int dayOfMonth) {
        Order order = new Order(dayOfMonth, menuOrder);
        EventsFactory eventsFactory = new EventsFactory(order);
        List<Event> events = eventsFactory.createEvents();
        BenefitCalculator benefitCalculator = new BenefitCalculator(events);
        BenefitResult benefitResult = benefitCalculator.calculateBenefit();
        return new BenefitResultDTO(benefitResult);
    }

    @DisplayName("이벤트가 4개가 적용 됐을 때 혜택 내역 출력한다.")
    @Test
    void printDiscountDetailsWhenFourEventsApplied() {
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.TBONE_STEAK, 1);
        menuOrder.put(MenuItem.BBQ_RIBS, 1);
        menuOrder.put(MenuItem.CHOCO_CAKE, 2);
        menuOrder.put(MenuItem.ZERO_COLA, 1);
        BenefitResultDTO benefitResultDTO = prepareDiscountResultDTO(menuOrder, 3);
        outputView.printBenefitResult(benefitResultDTO);
        String expectedOutput = Messages.BENEFIT_DETAILS_TITLE
                + "\n크리스마스 디데이 할인: -1,200원\n평일 할인: -4,046원\n특별 할인: -1,000원\n증정 이벤트: -25,000원\n\n";
        assertThat(outContent.toString()).contains(expectedOutput);
    }

    @DisplayName("이벤트가 3개가 적용 됐을 때 혜택 내역 출력한다.")
    @Test
    void printDiscountDetailsWhenThreeEventsApplied() {
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.TBONE_STEAK, 1);
        menuOrder.put(MenuItem.BBQ_RIBS, 1);
        menuOrder.put(MenuItem.CHOCO_CAKE, 2);
        menuOrder.put(MenuItem.ZERO_COLA, 1);
        BenefitResultDTO benefitResultDTO = prepareDiscountResultDTO(menuOrder, 31);
        outputView.printBenefitResult(benefitResultDTO);
        String expectedOutput =
                Messages.BENEFIT_DETAILS_TITLE + "\n평일 할인: -4,046원\n특별 할인: -1,000원\n증정 이벤트: -25,000원\n\n";
        assertThat(outContent.toString()).contains(expectedOutput);
    }

    @DisplayName("이벤트가 2개가 적용 됐을 때 혜택 내역 출력한다.")
    @Test
    void printDiscountDetailsWhenTwoEventsApplied() {
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.TBONE_STEAK, 1);
        menuOrder.put(MenuItem.BBQ_RIBS, 1);
        menuOrder.put(MenuItem.CHOCO_CAKE, 2);
        menuOrder.put(MenuItem.ZERO_COLA, 1);
        BenefitResultDTO benefitResultDTO = prepareDiscountResultDTO(menuOrder, 26);
        outputView.printBenefitResult(benefitResultDTO);
        String expectedOutput = Messages.BENEFIT_DETAILS_TITLE + "\n평일 할인: -4,046원\n증정 이벤트: -25,000원\n\n";
        assertThat(outContent.toString()).isEqualTo(expectedOutput);
    }

    @DisplayName("이벤트가 1개가 적용 됐을 때 혜택 내역 출력한다.")
    @Test
    void printDiscountDetailsWhenOneEventApplied() {
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.ICE_CREAM, 1);
        menuOrder.put(MenuItem.ZERO_COLA, 2);
        BenefitResultDTO benefitResultDTO = prepareDiscountResultDTO(menuOrder, 26);
        outputView.printBenefitResult(benefitResultDTO);
        String expectedOutput = Messages.BENEFIT_DETAILS_TITLE + "\n평일 할인: -2,023원\n\n";
        assertThat(outContent.toString()).isEqualTo(expectedOutput);
    }

    @DisplayName("이벤트가 아무것도 없을 시 혜택 내역 출력한다.")
    @Test
    void printDiscountDetailsWhenNoEventsApplied() {
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.MUSHROOM_SOUP, 1);
        menuOrder.put(MenuItem.ZERO_COLA, 1);
        BenefitResultDTO benefitResultDTO = prepareDiscountResultDTO(menuOrder, 26);
        outputView.printBenefitResult(benefitResultDTO);
        String expectedOutput = Messages.BENEFIT_DETAILS_TITLE + "\n없음\n\n";
        assertThat(outContent.toString()).isEqualTo(expectedOutput);
    }
}
