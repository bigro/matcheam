package matcheam.match;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.operation.Operation;
import matcheam.support.TestContext;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ooguro on 2017/07/08.
 */
public class MatchRepositoryRegisterTest {

	private MatchRepository sut;
	private TestContext testContext;

	@Before
	public void setUp() throws Exception {
		testContext = new TestContext();
		sut = new MatchRepository(testContext.dslContext());
		Operation deleteAll = deleteAllFrom("MATCHEAM.MATCH");
		DbSetup dbSetup = new DbSetup(testContext.driverManagerDestination(), deleteAll);
		dbSetup.launch();
	}

	@Test
	public void 募集内容を登録できること() throws Exception {
		Match match = getRecord();
		Identifier identifier = sut.register(match);
		Match actual = sut.findBy(identifier);
		assertThat(actual)
			.extracting("place", "date", "time", "level", "start")
			.containsOnly("場所", LocalDate.of(2017, 1, 25), "2時間", Level.LEVEL1, "昼ぐらい");
	}

	private Match getRecord() {
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


