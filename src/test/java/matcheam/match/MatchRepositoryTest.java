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
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ooguro on 2017/07/08.
 */
public class MatchRepositoryTest {

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
	public void 全件検索できること() throws Exception {
		Operation operation =
			insertIntoMatcheam()
				.values("尼崎", LocalDate.of(2017, 7, 7), "12時", "2時間", Level.LEVEL1, 12)
				.values("神戸", LocalDate.of(2017, 7, 7), "12時", "2時間", Level.LEVEL1, 12)
				.values("フランス", LocalDate.of(2017, 7, 7), "12時", "2時間", Level.LEVEL1, 12)
				.build();
		DbSetup dbSetup = new DbSetup(testContext.driverManagerDestination(), operation);
		dbSetup.launch();
		List<Match> actual = matchRepository.findAll();
		assertThat(actual).hasSize(3);
	}

	@Test
	public void 指定したレベル1つで絞り混んだ検索ができること() throws Exception {
		Operation operation =
			insertIntoMatcheam()
				.values("尼崎", LocalDate.of(2017, 7, 7), "12時", "2時間", Level.LEVEL1, 12)
				.values("神戸", LocalDate.of(2017, 7, 7), "12時", "2時間", Level.LEVEL3, 12)
				.values("フランス", LocalDate.of(2017, 7, 7), "12時", "2時間", Level.LEVEL4, 12)
				.build();
		DbSetup dbSetup = new DbSetup(testContext.driverManagerDestination(), operation);
		dbSetup.launch();

		List<Match> actual = matchRepository.findBy(Level.LEVEL3);
		assertThat(actual).extracting(Match::getLevel).containsOnly(Level.LEVEL3);
	}

	@Test
	public void 指定したレベルの募集が存在しない場合空のリストが返ってくること() throws Exception {
		Operation operation =
			insertIntoMatcheam()
				.values("尼崎", LocalDate.of(2017, 7, 7), "12時", "2時間", Level.LEVEL1, 12)
				.values("神戸", LocalDate.of(2017, 7, 7), "12時", "2時間", Level.LEVEL3, 12)
				.values("フランス", LocalDate.of(2017, 7, 7), "12時", "2時間", Level.LEVEL4, 12)
				.build();
		DbSetup dbSetup = new DbSetup(testContext.driverManagerDestination(), operation);
		dbSetup.launch();
		List<Match> actual = matchRepository.findBy(Level.LEVEL2);
		assertThat(actual).isEmpty();
	}

	@Test
	public void 指定したIDで絞り混んだ検索ができること() throws Exception {
		Operation operation =
			insertInto("MATCHEAM.MATCH")
				.columns("identifier", "place", "date", "start", "time", "level", "maxPlayers")
				.values(1, "尼崎", LocalDate.of(2017, 7, 7), "12時", "2時間", Level.LEVEL1, 12)
				.build();
		DbSetup dbSetup = new DbSetup(testContext.driverManagerDestination(), operation);
		dbSetup.launch();

		Match actual = matchRepository.findBy(new Identifier("1"));
		assertThat(actual).isNotNull();
	}

	@Test
	public void 指定したIDの募集が存在しない場合nullが返ってくること() throws Exception {
		Match actual = matchRepository.findBy(new Identifier("X"));
		assertThat(actual).isNull();
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

	private Insert.Builder insertIntoMatcheam() {
		return insertInto("MATCHEAM.MATCH")
			.columns("place", "date", "start", "time", "level", "maxPlayers");
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


