package christmas.validator;

import christmas.constants.Messages;
import christmas.constants.Values;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MenuOrderValidator {
    public static void validate(String input) {
        if (endsWithSeparator(input)) {
            throw new IllegalArgumentException(Messages.INVALID_ORDER_ERROR_MESSAGE);
        }
        List<String> items = Arrays.asList(input.split(Values.ITEM_SEPARATOR));
        if (isInvalidOrderFormat(items) || hasDuplicateItems(items)) {
            throw new IllegalArgumentException(Messages.INVALID_ORDER_ERROR_MESSAGE);
        }
    }

    private static boolean endsWithSeparator(String input) {
        return input.endsWith(Values.ITEM_SEPARATOR);
    }

    private static boolean isInvalidOrderFormat(List<String> items) {
        return items.stream()
                .anyMatch(item -> !item.matches(Values.MENU_ITEM_PATTERN));
    }


    private static boolean hasDuplicateItems(List<String> items) {
        Set<String> uniqueItems = items.stream()
                .map(item -> item.split(Values.MENU_QUANTITY_SEPARATOR)[0].trim())
                .collect(Collectors.toSet());
        return uniqueItems.size() < items.size();
    }
}
