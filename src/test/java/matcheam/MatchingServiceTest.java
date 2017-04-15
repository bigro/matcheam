package matcheam;

import matcheam.match.Identifier;
import matcheam.match.Match;
import matcheam.matching.Matching;
import matcheam.matching.MatchingService;
import matcheam.person.Person;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ooguro on 2017/01/07.
 */
public class MatchingServiceTest {

	private MatchingService matchingService = new MatchingService();

	@Test
	public void 募集に対して応募できること() throws Exception {
		Match match = match(new Identifier("1"));
		Person entryUser = new Person();
		entryUser.setIdentifier(new Identifier("1"));
		entryUser.setName("名前");
		matchingService.matching(match, entryUser);

		Matching actual = matchingService.get(match);
		assertThat(actual.getMatch()).isEqualTo(match);
		assertThat(actual.getEntryUsers())
			.extracting(Person::getName)
			.contains("名前");
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
