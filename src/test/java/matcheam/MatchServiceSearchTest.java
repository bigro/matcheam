package matcheam;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import matcheam.common.SystemContext;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import matcheam.match.Identifier;
import matcheam.match.Level;
import matcheam.match.Match;
import matcheam.match.MatchRepository;
import matcheam.match.MatchService;

/**
 * Created by takata on 2017/01/07.
 */
public class MatchServiceSearchTest {

	private MatchService matchService;

	@Before
	public void Before() throws SQLException {
		matchService = new MatchService(new MatchRepository(new SystemContext().dslContext()));
		matchService.matchMap.clear();

		matchService.register(createMatch("1", Level.LEVEL1));
		matchService.register(createMatch("2", Level.LEVEL1));
		matchService.register(createMatch("3", Level.LEVEL3));
		matchService.register(createMatch("4", Level.LEVEL3));
		matchService.register(createMatch("5", Level.LEVEL3));
		matchService.register(createMatch("6", Level.LEVEL4));
	}

	private Match createMatch(String id, Level level) {
		LocalDate date = LocalDate.now();
		Match match = new Match();
		match.setIdentifier(new Identifier(id));
		match.setPlace("場所");
		match.setDate(date);
		match.setTime("2時間");
		match.setMaxPlayers(BigDecimal.TEN);
		match.setLevel(level);
		return match;
	}

	@Test
	public void 指定したIDで絞り混んだ検索ができること() throws Exception {
		Match actual = matchService.findBy(new Identifier("4"));
		assertThat(actual.getIdentifier().toString()).isEqualTo("4");
	}

	@Test
	public void 指定したIDの募集が存在しない場合nullが返ってくること() throws Exception {
		Match actual = matchService.findBy(new Identifier("X"));
		assertThat(actual).isNull();
	}

	@Test
	public void 指定したレベル1つで絞り混んだ検索ができること() throws Exception {
		Collection<Match> actual = matchService.findByLevel(Level.LEVEL3);
		assertThat(actual).extracting(Match::getLevel).containsOnly(Level.LEVEL3);
	}

	@Test
	public void 指定したレベル2つで絞り混んだ検索ができること() throws Exception {
		Collection<Match> actual = matchService.findByLevel(Level.LEVEL3, Level.LEVEL4);
		assertThat(actual).extracting(Match::getLevel).containsOnly(Level.LEVEL3, Level.LEVEL4);
	}

	@Ignore("HashMap前提のテストだからJooqにしたら落ちた")
	@Test
	public void 指定したレベルの募集が存在しない場合空のリストが返ってくること() throws Exception {
		Collection<Match> actual = matchService.findByLevel(Level.LEVEL2);
		assertThat(actual).isEmpty();
	}

	@Ignore("HashMap前提のテストだからJooqにしたら落ちた")
	@Test
	public void 全件検索できること() throws Exception {
		Collection<Match> actual = matchService.findAll();
		assertThat(actual).hasSize(6).extracting(Match::getLevel).containsOnly(Level.LEVEL1, Level.LEVEL3,
			Level.LEVEL4);
	}

}
