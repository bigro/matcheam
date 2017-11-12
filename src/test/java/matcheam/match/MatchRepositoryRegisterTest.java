package matcheam.match;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.operation.Operation;
import matcheam.entry.EntryRepository;
import matcheam.support.TestSupport;
import matcheam.support.TestDataSource;
import org.junit.Before;
import org.junit.Test;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ooguro on 2017/07/08.
 */
public class MatchRepositoryRegisterTest {

    private MatchRepository sut;
    private TestDataSource testDataSource;

    @Before
    public void setUp() throws Exception {
        testDataSource = new TestDataSource();
        EntryRepository entryRepository = new EntryRepository(testDataSource.dslContext());
        sut = new MatchRepository(testDataSource.dslContext(), entryRepository);
        Operation deleteAll = sequenceOf(
                deleteAllFrom("MATCHEAM.ENTRY_USER"),
                deleteAllFrom("MATCHEAM.MATCH")
        );
        DbSetup dbSetup = new DbSetup(testDataSource, deleteAll);
        dbSetup.launch();
    }

    @Test
    public void 募集内容を登録できること() throws Exception {
        Match match = TestSupport.newMatch();
        Identifier actual = sut.register(match);
        assertRegister(actual, match);
    }

    private void assertRegister(Identifier actual, Match match) throws Exception {
        assertThat(sut.findBy(actual))
                .extracting("place", "date", "time", "level", "start")
                .containsOnly(match.getPlace(), match.getDate(), match.getTime(), match.getLevel(), match.getStart());
    }
}


