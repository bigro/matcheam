package matcheam;

import java.math.BigDecimal;
import java.time.LocalDate;

import matcheam.common.SystemContext;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import matcheam.match.Identifier;
import matcheam.match.Level;
import matcheam.match.Match;
import matcheam.match.MatchRepository;
import matcheam.match.MatchService;

/**
 * Created by ooguro on 2017/01/07.
 */
public class MatchServiceTest {

	private MatchService matchService;

	@Before
	public void setUp() throws Exception {
		matchService = new MatchService(new MatchRepository(new SystemContext().dslContext()));
	}

	@Test
	public void 募集内容を登録できること() throws Exception {
		Match match = match();

		Identifier identifier = matchService.register(match);

		Match actual = matchService.findBy(identifier);
		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(actual.getPlace()).isEqualTo("場所");
		softAssertions.assertThat(actual.getDate()).isEqualTo(LocalDate.of(2017, 1, 25));
		softAssertions.assertThat(actual.getTime()).isEqualTo("2時間");
		softAssertions.assertThat(actual.getLevel()).isEqualTo(Level.LEVEL1);
		softAssertions.assertThat(actual.getStart()).isEqualTo("昼ぐらい");
		softAssertions.assertAll();
	}

	private Match match() {
		Match match = new Match();
		match.setPlace("場所");
		match.setStart("昼ぐらい");
		match.setDate(LocalDate.of(2017, 1, 25));
		match.setTime("2時間");
		match.setLevel(Level.LEVEL1);
		match.setMaxPlayers(BigDecimal.TEN);
		return match;
	}
}
