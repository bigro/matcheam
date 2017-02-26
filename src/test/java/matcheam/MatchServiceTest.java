package matcheam;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Created by ooguro on 2017/01/07.
 */
public class MatchServiceTest {

	private MatchService matchService = new MatchService();

	@Test
	public void 募集内容を登録できること() throws Exception {
		Match match = match(new Identifier("1"));

		matchService.register(match);

		Match actual = matchService.matchMap.get("1");
		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(actual.getIdentifier().toString()).isEqualTo("1");
		softAssertions.assertThat(actual.getPlace()).isEqualTo("場所");
		softAssertions.assertThat(actual.getDate()).isEqualTo(LocalDateTime.of(2017, 01, 25, 1, 0));
		softAssertions.assertThat(actual.getGameTime()).isEqualTo(Duration.ofHours(2));
		softAssertions.assertAll();
	}

	private Match match(Identifier identifier) {
		Match match = new Match();
		match.setIdentifier(identifier);
		match.setPlace("場所");
		match.setDate(LocalDateTime.of(2017, 01, 25, 1, 0));
		match.setGameTime(Duration.ofHours(2));
		match.setMaxPlayers(BigDecimal.TEN);
		return match;
	}
}
