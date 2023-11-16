package christmas.view;

import christmas.DTO.BenefitResultDTO;
import christmas.DTO.OrderDTO;
import christmas.constants.Messages;
import christmas.constants.Values;
import java.util.stream.Collectors;

public class OutputView {
    public void printEventStartGreeting() {
        System.out.println(Messages.EVENT_PLANNER_GREETING);
    }

    public void printOrderInformation(OrderDTO orderDTO) {
        System.out.println(String.format(Messages.EVENT_BENEFITS_PREVIEW_FORMAT, orderDTO.date()));
        System.out.println(Messages.ORDER_MENU_TITLE);
        String menuDetails = orderDTO.menu().entrySet().stream()
                .map(entry -> String.format(Values.MENU_ITEM_FORMAT, entry.getKey().getKoreanName(), entry.getValue()))
                .collect(Collectors.joining("\n", "", "\n"));
        System.out.println(menuDetails);
    }

    public void printTotalOrderAmountBeforeDiscount(int amount) {
        System.out.println(Messages.ORDER_AMOUNT_BEFORE_DISCOUNT_MESSAGE);
        System.out.println(String.format(Values.FORMATTED_CURRENCY_AMOUNT, amount));
        System.out.println();
    }

    public void printGiveawayMenu(String giveawayMenu) {
        System.out.println(Messages.GIVEAWAY_MENU_TITLE);
        System.out.println(giveawayMenu);
        System.out.println();
    }

    public void printBenefitResult(BenefitResultDTO benefitResultDTO) {
        System.out.println(Messages.BENEFIT_DETAILS_TITLE);
        if (benefitResultDTO.isAllZeroValues()) {
            System.out.println(Values.NOT_EXIST);
            System.out.println();
            return;
        }
        printNonZeroBenefitEvents(benefitResultDTO);
    }

    private static void printNonZeroBenefitEvents(BenefitResultDTO benefitResultDTO) {
        benefitResultDTO.benefitResult().entrySet().stream()
                .filter(entry -> entry.getValue() != 0)
                .map(entry -> String.format(Values.BENEFIT_DETAILS, entry.getKey(),
                        String.format(Values.FORMATTED_CURRENCY_AMOUNT, entry.getValue())))
                .forEach(System.out::println);
        System.out.println();
    }

    public void printTotalBenefitAmount(int amount) {
        System.out.println(Messages.TOTAL_BENEFIT_AMOUNT_TITLE);
        System.out.println(String.format(Values.FORMATTED_CURRENCY_AMOUNT, amount));
        System.out.println();
    }

    public void printPaymentAmountAfterDiscount(int amount) {
        System.out.println(Messages.TOTAL_AMOUNT_AFTER_DISCOUNT_TITLE);
        System.out.println(String.format(Values.FORMATTED_CURRENCY_AMOUNT, amount));
        System.out.println();
    }

    public void printBadgeResult(String badge) {
        System.out.println(Messages.BADGE_EVENT_TITLE);
        System.out.println(badge);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}
