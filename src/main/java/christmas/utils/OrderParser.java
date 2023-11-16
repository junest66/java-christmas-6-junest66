package christmas.utils;

import christmas.constants.Values;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderParser {
    public static Map<String, Integer> parse(String input) {
        return Arrays.stream(input.split(Values.ITEM_SEPARATOR))
                .map(item -> item.split(Values.MENU_QUANTITY_SEPARATOR))
                .collect(Collectors.toMap(
                        parts -> parts[0],
                        parts -> Integer.parseInt(parts[1])
                ));
    }
}
