package matcheam;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;

import matcheam.match.Identifier;
import matcheam.match.Match;
import matcheam.matching.Matching;
import matcheam.matching.MatchingService;
import matcheam.person.Person;

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
		assertThat(actual.getPersons())
			.extracting(Person::getName)
			.contains("名前");
	}

	private Match match(Identifier identifier) {
		Match match = new Match();
		match.setIdentifier(identifier);
		match.setPlace("場所");
		match.setDate(LocalDate.of(2017, 1, 25));
		match.setTime("2時間");
		match.setMaxPlayers(BigDecimal.TEN);
		return match;
	}
}
