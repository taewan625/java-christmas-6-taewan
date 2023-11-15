package christmas.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class XmasValidatorTest {
    @Test
    void formatCheck() {
        List<String[]> collectFormat = List.of(new String[]{"샴페인", "3"}, new String[]{"타파스", "3"});
        List<String[]> wrongFormat = List.of(new String[]{"샴페인"}, new String[]{"타파스", "3"});
        assertThat(XmasValidator.formatCheck(collectFormat)).isSameAs(collectFormat);
        assertThatThrownBy(() -> XmasValidator.formatCheck(wrongFormat))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void orderMenu() {
        Set<String> collectMenu = Set.of("양송이수프", "시저샐러드", "타파스");
        List<String> collectCount = List.of("1", "2", "3");

        Set<String> onlyDrink = Set.of("레드와인", "제로콜라", "샴폐인");
        Set<String> noMenu = Set.of("양송이수프", "asdf", "샴폐인");
        List<String> moreCount = List.of("1", "2", "3", "5");
        List<String> overCount = List.of("1", "2", "21");
        List<String> overSumCount = List.of("1", "2", "18");

        XmasValidator.orderMenu(collectMenu, collectCount);
        assertThatThrownBy(() -> XmasValidator.orderMenu(collectMenu, moreCount))
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> XmasValidator.orderMenu(collectMenu, overCount))
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> XmasValidator.orderMenu(collectMenu, overSumCount))
                .isInstanceOf(IllegalStateException.class);
        XmasValidator.orderMenu(collectMenu, collectCount);
        assertThatThrownBy(() -> XmasValidator.orderMenu(onlyDrink, collectCount))
                .isInstanceOf(IllegalStateException.class);
        assertThatThrownBy(() -> XmasValidator.orderMenu(noMenu, collectCount))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void date() {
        String firstDate = "1";
        String lastDate = "31";
        String noDate = "a";
        String overDate = "32";
        String zero = "0";
        String minusDate = "-1";

        XmasValidator.date(firstDate);
        XmasValidator.date(lastDate);

        Assertions.assertThatThrownBy(() -> XmasValidator.date(noDate))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> XmasValidator.date(overDate))
                .isInstanceOf(IllegalStateException.class);
        Assertions.assertThatThrownBy(() -> XmasValidator.date(zero))
                .isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> XmasValidator.date(minusDate))
                .isInstanceOf(IllegalArgumentException.class);
    }
}