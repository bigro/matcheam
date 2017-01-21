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

		matchService.register(createMatch("1", Level.LEVEL1));
		matchService.register(createMatch("2", Level.LEVEL1));
		matchService.register(createMatch("3", Level.LEVEL3));
		matchService.register(createMatch("4", Level.LEVEL3));
		matchService.register(createMatch("5", Level.LEVEL3));
		matchService.register(createMatch("6", Level.LEVEL4));
	}

	private Match createMatch(String id, Level level) {
		LocalDateTime date = LocalDateTime.now();
		Duration gameTime = Duration.ofHours(2);
		Match match = new Match();
		match.setIdentifier(new Identifier(id));
		match.setPlace("場所");
		match.setDate(date);
		match.setGameTime(gameTime);
		match.setMaxPlayers(10);
		match.setLevel(level);
		return match;
	}

	@Test
	public void 指定したIDで絞り混んだ検索ができること() throws Exception {
		Match actual = matchService.findOne("4");
		assertThat(actual.getIdentifier().toString()).isEqualTo("4");
	}

	@Test
	public void 指定したIDの募集が存在しない場合nullが返ってくること() throws Exception {
		Match actual = matchService.findOne("X");
		assertThat(actual).isNull();
	}

	@Test
	public void 指定したレベル1つで絞り混んだ検索ができること() throws Exception {
		Collection<Match> actual = matchService.findByLevel(Level.LEVEL3);
		assertThat(actual).hasSize(3).extracting(Match::getLevel).containsOnly(Level.LEVEL3);
	}

	@Test
	public void 指定したレベル2つで絞り混んだ検索ができること() throws Exception {
		Collection<Match> actual = matchService.findByLevel(Level.LEVEL3, Level.LEVEL4);
		assertThat(actual).hasSize(4).extracting(Match::getLevel).containsOnly(Level.LEVEL3, Level.LEVEL4);
	}

	@Test
	public void 指定したレベルの募集が存在しない場合空のリストが返ってくること() throws Exception {
		Collection<Match> actual = matchService.findByLevel(Level.LEVEL2);
		assertThat(actual).isEmpty();
	}

	@Test
	public void 全件検索できること() throws Exception {
		Collection<Match> actual = matchService.findAll();
		assertThat(actual).hasSize(6).extracting(Match::getLevel).containsOnly(Level.LEVEL1, Level.LEVEL3,
				Level.LEVEL4);
	}

}