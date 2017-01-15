package matcheam;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by ooguro on 2017/01/07.
 */
public class MatchServiceTest {

    private MatchService matchService = new MatchService();

    @Test
    public void 募集内容を登録できること() throws Exception {
        LocalDateTime date = LocalDateTime.now();
        Duration gameTime = Duration.ofHours(2);
        Match match = new Match();
        match.setIdentifier(new Identifier("1"));
        match.setPlace("場所");
        match.setDate(date);
        match.setGameTime(gameTime);
        match.setMaxPlayers(10);

        matchService.register(match);

        Match actual = matchService.matchMap.get("1");
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actual.getIdentifier().toString()).isEqualTo("1");
        softAssertions.assertThat(actual.getPlace()).isEqualTo("場所");
        softAssertions.assertThat(actual.getDate()).isEqualTo(date);
        softAssertions.assertThat(actual.getGameTime()).isEqualTo(gameTime);
        softAssertions.assertAll();
    }
}