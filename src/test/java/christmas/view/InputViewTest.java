package christmas.view;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.Messages;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputView inputView = new InputView();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        Console.close();
    }

    @DisplayName("사용자에 날짜를 입력받아 Integer형으로 반환한다.")
    @Test
    void shouldReturnDateFromUserInput() {
        String input = "10";
        int expectedValue = 10;
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertThat(inputView.getDecemberDay()).isEqualTo(expectedValue);
        assertThat(outContent.toString()).contains(Messages.EXPECTED_DECEMBER_VISIT_DATE_PROMPT);
    }

    @DisplayName("사용자에 메뉴주문을 입력받아 Map<String,Integer> 으로 반환한다.")
    @Test
    void shouldReturnMenuOrderFromUserInput() {
        String input = "타파스-1,해물파스타-2";
        Map<String, Integer> expectedValue = new HashMap<>();
        expectedValue.put("타파스", 1);
        expectedValue.put("해물파스타", 2);
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertThat(inputView.getMenuOrder()).isEqualTo(expectedValue);
        assertThat(outContent.toString()).contains(Messages.REQUEST_MENU_SELECTION_AND_QUANTITY_PROMPT);
    }
}
