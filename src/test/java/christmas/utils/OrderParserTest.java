package christmas.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderParserTest {

    @DisplayName("문자열 입력이 들어왔을때 메뉴명과 수량을 Map형태로 key value로 파싱한다.")
    @Test
    void parse() {
        String input = "타파스-1,해물파스타-5,제로콜라-2";
        Map<String, Integer> expectedValue = new HashMap<>();
        expectedValue.put("타파스", 1);
        expectedValue.put("해물파스타", 5);
        expectedValue.put("제로콜라", 2);
        Map<String, Integer> menu = OrderParser.parse(input);
        assertThat(menu).isEqualTo(expectedValue);
    }
}
