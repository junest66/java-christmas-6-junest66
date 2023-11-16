package christmas.constants;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public enum MenuItem {
    MUSHROOM_SOUP("양송이수프", 6000, MenuCategory.APPETIZER),
    TAPAS("타파스", 5500, MenuCategory.APPETIZER),
    CAESAR_SALAD("시저샐러드", 8000, MenuCategory.APPETIZER),
    TBONE_STEAK("티본스테이크", 55000, MenuCategory.MAIN),
    BBQ_RIBS("바비큐립", 54000, MenuCategory.MAIN),
    SEAFOOD_PASTA("해산물파스타", 35000, MenuCategory.MAIN),
    CHRISTMAS_PASTA("크리스마스파스타", 25000, MenuCategory.MAIN),
    CHOCO_CAKE("초코케이크", 15000, MenuCategory.DESSERT),
    ICE_CREAM("아이스크림", 5000, MenuCategory.DESSERT),
    ZERO_COLA("제로콜라", 3000, MenuCategory.BEVERAGE),
    RED_WINE("레드와인", 60000, MenuCategory.BEVERAGE),
    CHAMPAGNE("샴페인", 25000, MenuCategory.BEVERAGE);

    private final String koreanName;
    private final int price;
    private final MenuCategory menuCategory;

    MenuItem(String koreanName, int price, MenuCategory menuCategory) {
        this.koreanName = koreanName;
        this.price = price;
        this.menuCategory = menuCategory;
    }

    public static boolean isOrderInvalid(Map<String, Integer> orderHistory) {
        return hasInvalidMenuItems(orderHistory) ||
                isOrderAllDrinks(orderHistory) ||
                hasExcessiveQuantity(orderHistory) ||
                hasInvalidQuantities(orderHistory);
    }

    public static Optional<MenuItem> findByKoreanName(String koreanName) {
        return Arrays.stream(MenuItem.values())
                .filter(menuItem -> menuItem.getKoreanName().equals(koreanName))
                .findFirst();
    }

    private static boolean hasInvalidMenuItems(Map<String, Integer> orderHistory) {
        return orderHistory.keySet().stream()
                .anyMatch(itemName -> MenuItem.findByKoreanName(itemName).isEmpty());
    }

    private static boolean isOrderAllDrinks(Map<String, Integer> orderHistory) {
        return orderHistory.keySet().stream()
                .allMatch(itemName -> MenuItem.findByKoreanName(itemName)
                        .map(menuItem -> menuItem.getMenuCategory() == MenuCategory.BEVERAGE)
                        .orElse(true));
    }

    private static boolean hasInvalidQuantities(Map<String, Integer> orderHistory) {
        return orderHistory.values().stream()
                .anyMatch(quantity -> quantity < Values.MINIMUM_MENU_QUANTITY);
    }

    private static boolean hasExcessiveQuantity(Map<String, Integer> orderHistory) {
        int totalQuantity = orderHistory.values().stream().mapToInt(Integer::intValue).sum();
        return totalQuantity > Values.MAXIMUM_TOTAL_QUANTITY;
    }

    public String getKoreanName() {
        return koreanName;
    }

    public int getPrice() {
        return price;
    }

    public MenuCategory getMenuCategory() {
        return menuCategory;
    }

}
