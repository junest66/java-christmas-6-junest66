package christmas.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateValidatorTest {
    private static final String INVALID_DATE_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    @DisplayName("숫자가 아니거나 정수형태가 아닌 입력은 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"ab", "", " ", "12a", "1.5", "5.%#"})
    void validateNonNumericInput(String input) {
        assertThatThrownBy(() -> DateValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DATE_MESSAGE);
    }

    @DisplayName("날짜 범위를 벗어난 입력은 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"0", "32", "35", "-1", "-5"})
    void validateInputOutsideDateRange(String input) {
        assertThatThrownBy(() -> DateValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DATE_MESSAGE);
    }

    @DisplayName("0으로 시작하는 날짜형태는 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"01", "02", "03", "05", "08"})
    void shouldThrowExceptionForDateStartingWithZero(String input) {
        assertThatThrownBy(() -> DateValidator.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DATE_MESSAGE);
    }

    @DisplayName("유효한 날짜 입력을 검증한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "5", "31"})
    void validateValidDateInput(String input) {
        assertDoesNotThrow(() -> DateValidator.validate(input));
    }
}
