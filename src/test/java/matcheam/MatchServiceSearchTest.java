package matcheam;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.operation.Operation;
import matcheam.common.SystemContext;
import matcheam.support.TestContext;
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
	private TestContext testContext;

	@Before
	public void Before() throws SQLException {
		testContext = new TestContext();
		Operation deleteAll = deleteAllFrom("MATCHEAM.MATCH");
		DbSetup dbSetup = new DbSetup(testContext.driverManagerDestination(), deleteAll);
		dbSetup.launch();

		matchService = new MatchService(new MatchRepository(new SystemContext().dslContext()));
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
	public void 指定したIDの募集が存在しない場合nullが返ってくること() throws Exception {
		Match actual = matchService.findBy(new Identifier("X"));
		assertThat(actual).isNull();
	}

}
