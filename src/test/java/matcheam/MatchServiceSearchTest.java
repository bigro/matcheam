package matcheam;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by takata on 2017/01/07.
 */
public class MatchServiceSearchTest {

	private MatchService matchService = new MatchService();

	@Before
	public void Before() {
		matchService.matchMap.clear();

		matchService.matchMap.put("1", createMatch(Level.LEVEL1));
		matchService.matchMap.put("2", createMatch(Level.LEVEL1));
		matchService.matchMap.put("3", createMatch(Level.LEVEL3));
		matchService.matchMap.put("4", createMatch(Level.LEVEL3));
		matchService.matchMap.put("5", createMatch(Level.LEVEL3));
	}

	private Match createMatch(Level level) {
		LocalDateTime date = LocalDateTime.now();
		Duration gameTime = Duration.ofHours(2);
		Match match = new Match();
		match.setIdentifier(new Identifier("1"));
		match.setPlace("場所");
		match.setDate(date);
		match.setGameTime(gameTime);
		match.setMaxPlayers(10);
		match.setLevel(level);
		return match;
	}

	@Test
	public void 指定したレベルで絞り混んだ検索ができること() throws Exception {
		Collection<Match> actual = matchService.findByLevel(Level.LEVEL3);
		assertThat(actual).hasSize(3).extracting(Match::getLevel).containsOnly(Level.LEVEL3);
	}

	@Test
	public void 指定したレベルの募集が存在しない場合空のリストが返ってくること() throws Exception {
		Collection<Match> actual = matchService.findByLevel(Level.LEVEL2);
		assertThat(actual).isEmpty();
	}

	@Test
	public void 全件検索できること() throws Exception {
		Collection<Match> actual = matchService.findAll();
		assertThat(actual).hasSize(5).extracting(Match::getLevel).containsOnly(Level.LEVEL1, Level.LEVEL3);
	}

}