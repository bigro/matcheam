package matcheam.support;

import matcheam.match.Level;
import matcheam.match.Match;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TestSupport {
    public static Match newMatch() {
        Match match = new Match();
        match.setPlace("場所");
        match.setStart("昼ぐらい");
        match.setDate(LocalDate.of(2017, 1, 25));
        match.setTime("2時間");
        match.setLevel(Level.LEVEL1);
        match.setMaxPlayers(BigDecimal.TEN);
        match.setRemarks("雨天中止");
        return match;
    }
}
