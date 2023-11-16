package christmas.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuOrderValidatorTest {
    private static final String INVALID_ORDER_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";

    @DisplayName("메뉴주문 형식에 맞지않은 입력은 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"asd1-1", "타파스 - 1", "타파스1", "타파스,1", "타파스-1 ", "타파스-1,해물파스타-ㅁ"})
    void shouldThrowExceptionForInvalidOrderFormat(String input) {
        assertThatThrownBy(() -> MenuOrderValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_MESSAGE);

    }

    @DisplayName("입력받은 문자열 마지막이 구분자이면 예외가 발생한다")
    @Test
    void shouldThrowExceptionWhenInputEndsWithSeparator() {
        String input = "타파스-1,제로콜라-1,";
        assertThatThrownBy(() -> MenuOrderValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_MESSAGE);
    }

    @DisplayName("메뉴주문에서 중복된 메뉴가 있으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-1,타파스-2", "해물파스타-3,타파스-1,해물파스타-5", "타파스-1,해물파스타-5,타파스-5,해물파스타-7"})
    void shouldThrowExceptionForDuplicateMenuItems(String input) {
        assertThatThrownBy(() -> MenuOrderValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_ORDER_MESSAGE);
    }

    @DisplayName("메뉴주문 정상입력을 검증한다.")
    @ParameterizedTest
    @ValueSource(strings = {"타파스-1,해물파스타-2", "타파스-1,해물파스타-2,제로콜라-6", "타파스-1,해물파스타-5,크림파스타-5,로제파스타-7"})
    void shouldPassValidationForValidOrders(String input) {
        assertDoesNotThrow(() -> MenuOrderValidator.validate(input));
    }
}
