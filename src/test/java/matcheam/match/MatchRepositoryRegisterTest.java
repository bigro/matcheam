package matcheam.match;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;
import matcheam.support.TestContext;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ooguro on 2017/07/08.
 */
public class MatchRepositoryRegisterTest {

	private MatchRepository matchRepository;
	private TestContext testContext;

	@Before
	public void setUp() throws Exception {
		testContext = new TestContext();
		matchRepository = new MatchRepository(testContext.dslContext());
		Operation deleteAll = deleteAllFrom("MATCHEAM.MATCH");
		DbSetup dbSetup = new DbSetup(testContext.driverManagerDestination(), deleteAll);
		dbSetup.launch();
	}

	@Test
	public void 募集内容を登録できること() throws Exception {
		Match match = getRecord();

		Identifier identifier = matchRepository.register(match);

		Match actual = matchRepository.findBy(identifier);
		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(actual.getPlace()).isEqualTo("場所");
		softAssertions.assertThat(actual.getDate()).isEqualTo(LocalDate.of(2017, 1, 25));
		softAssertions.assertThat(actual.getTime()).isEqualTo("2時間");
		softAssertions.assertThat(actual.getLevel()).isEqualTo(Level.LEVEL1);
		softAssertions.assertThat(actual.getStart()).isEqualTo("昼ぐらい");
		softAssertions.assertAll();
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


