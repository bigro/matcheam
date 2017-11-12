package matcheam.entry;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.operation.Operation;
import matcheam.match.*;
import matcheam.support.TestSupport;
import matcheam.support.TestDataSource;
import org.junit.Before;
import org.junit.Test;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

public class EntryRepositoryTest {
    private TestDataSource testDataSource;

    EntryRepository sut;
    MatchService matchService;

    @Before
    public void setUp() throws Exception {
        testDataSource = new TestDataSource();
        sut = new EntryRepository(testDataSource.dslContext());
        matchService = new MatchService(new MatchRepository(testDataSource.dslContext(), sut));
        Operation deleteAll = sequenceOf(
                deleteAllFrom("MATCHEAM.ENTRY_USER"),
                deleteAllFrom("MATCHEAM.MATCH")
        );
        DbSetup dbSetup = new DbSetup(testDataSource, deleteAll);
        dbSetup.launch();
    }

    @Test
    public void 応募を登録して検索できること() throws Exception {
        Identifier matchId = matchService.register(TestSupport.newMatch());

        sut.register(new Entry(Match.of(matchId), "nishida"));

        Match match = matchService.findBy(matchId);
        assertThat(match).isNotNull();
        assertThat(match.getEntryUserList()).hasSize(1);
    }
}