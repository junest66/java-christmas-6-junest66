package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constants.MenuItem;
import christmas.constants.MenuCategory;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {
    @DisplayName("특정 카테고리의 메뉴가 몇 개있는지 계산한다")
    @Test
    void shouldCorrectlyCountNumberOfMenuItemsInEachCategory() {
        int date = 5;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.SEAFOOD_PASTA, 5);
        menuOrder.put(MenuItem.ZERO_COLA, 2);
        menuOrder.put(MenuItem.TAPAS, 1);
        menuOrder.put(MenuItem.RED_WINE, 1);
        Order order = new Order(date, menuOrder);
        assertThat(order.getMenuCategoryCount(MenuCategory.BEVERAGE)).isEqualTo(3);
        assertThat(order.getMenuCategoryCount(MenuCategory.APPETIZER)).isEqualTo(1);
        assertThat(order.getMenuCategoryCount(MenuCategory.MAIN)).isEqualTo(5);
    }

    @DisplayName("할인전 총 주문 금액이 얼마 인지 계산한다")
    @Test
    void shouldCorrectlyCalculateTotalOrderAmountBeforeDiscount() {
        int date = 5;
        Map<MenuItem, Integer> menuOrder = new HashMap<>();
        menuOrder.put(MenuItem.SEAFOOD_PASTA, 5);
        menuOrder.put(MenuItem.ZERO_COLA, 2);
        menuOrder.put(MenuItem.TAPAS, 1);
        menuOrder.put(MenuItem.RED_WINE, 1);
        Order order = new Order(date, menuOrder);
        int expectedValue =
                MenuItem.SEAFOOD_PASTA.getPrice() * 5 + MenuItem.ZERO_COLA.getPrice() * 2 + MenuItem.TAPAS.getPrice()
                        + MenuItem.RED_WINE.getPrice();
        assertThat(order.calculateTotalPriceBeforeDiscount()).isEqualTo(expectedValue);
    }
}
