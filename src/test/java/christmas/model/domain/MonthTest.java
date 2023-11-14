package christmas.model.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static christmas.model.domain.Month.*;
import static org.assertj.core.api.Assertions.assertThat;

class MonthTest {

    @Test
    void applyDiscountByDate() {
        int dDayWeekend = 1;
        int dDayWeekdayStarDay = 25;
        int weekDay = 26;
        int starDay = 31;

        List<String> dDayWeekends = Month.applyDiscountByDate(dDayWeekend);
        List<String> dDayWeekdayStarDays = Month.applyDiscountByDate(dDayWeekdayStarDay);
        List<String> weekDays = Month.applyDiscountByDate(weekDay);
        List<String> starDays = Month.applyDiscountByDate(starDay);

        assertThat(dDayWeekends).contains(D_DAY.name(), WEEKEND.name());
        assertThat(dDayWeekdayStarDays).contains(D_DAY.name(), WEEKDAY.name(), STAR_DAY.name());
        assertThat(weekDays).contains(WEEKDAY.name());
        assertThat(starDays).contains(STAR_DAY.name());
    }
}