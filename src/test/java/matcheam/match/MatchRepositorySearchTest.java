package matcheam.match;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.operation.Operation;
import matcheam.AbstractTest;
import matcheam.entry.EntryRepository;
import matcheam.support.TestDataSource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ooguro on 2017/07/08.
 */
public class MatchRepositorySearchTest extends AbstractTest {
	private MatchRepository sut;

	@Before
	public void setUp() throws Exception {
		Operation operation =
			sequenceOf(
				deleteAllFrom("MATCHEAM.MATCH"),
				insertInto("MATCHEAM.MATCH")
					.columns("identifier", "place", "date", "start", "time", "level", "max_players")
					.values(1, "尼崎", LocalDate.of(2017, 7, 7), "12時", "2時間", Level.LEVEL1, 12)
					.values(2, "神戸", LocalDate.of(2017, 7, 7), "12時", "2時間", Level.LEVEL3, 12)
					.values(3, "フランス", LocalDate.of(2017, 7, 7), "12時", "2時間", Level.LEVEL4, 12)
					.build()
			);
		DbSetup dbSetup = new DbSetup(testDataSource, operation);
		dbSetup.launch();

		EntryRepository entryRepository = new EntryRepository(testDataSource.dslContext());
		sut = new MatchRepository(testDataSource.dslContext(), entryRepository);
	}

	@Test
	public void 全件検索できること() throws Exception {
		List<Match> actual = sut.findAll();
		assertThat(actual).hasSize(3);
	}

	@Test
	public void 指定したレベル1つで絞り混んだ検索ができること() throws Exception {
		List<Match> actual = sut.findBy(Level.LEVEL3);
		assertThat(actual).extracting(Match::getLevel).containsOnly(Level.LEVEL3);
	}

	@Test
	public void 指定したレベルの募集が存在しない場合空のリストが返ってくること() throws Exception {
		List<Match> actual = sut.findBy(Level.LEVEL2);
		assertThat(actual).isEmpty();
	}

	@Test
	public void 指定したIDで絞り混んだ検索ができること() throws Exception {
		Match actual = sut.findBy(new Identifier("1"));
		assertThat(actual).isNotNull();
	}

	@Test
	public void 指定したIDの募集が存在しない場合nullが返ってくること() throws Exception {
		Match actual = sut.findBy(new Identifier(99999));
		assertThat(actual).isNull();
	}
}
