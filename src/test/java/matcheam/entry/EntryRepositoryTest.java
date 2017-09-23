package matcheam.entry;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.operation.Operation;
import matcheam.match.Identifier;
import matcheam.match.Match;
import matcheam.match.MatchRepository;
import matcheam.match.MatchService;
import matcheam.support.TestContext;
import org.junit.Before;
import org.junit.Test;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

public class EntryRepositoryTest {
    private TestContext testContext;

    EntryRepository sut;
    MatchService matchService;

    @Before
    public void setUp() throws Exception {
        testContext = new TestContext();
        sut = new EntryRepository(testContext.dslContext());
        matchService = new MatchService(new MatchRepository(testContext.dslContext(), sut));
        Operation deleteAll = sequenceOf(
                deleteAllFrom("MATCHEAM.ENTRY_USER"),
                deleteAllFrom("MATCHEAM.ENTRY")
        );
        DbSetup dbSetup = new DbSetup(testContext.driverManagerDestination(), deleteAll);
        dbSetup.launch();
    }

    @Test
    public void 応募を登録して検索できること() throws Exception {
        sut.register(new Entry(Match.of(Identifier.of(2)), "nishida"));

        Match match = matchService.findBy(Match.of(Identifier.of(2)).getIdentifier());
        assertThat(match).isNotNull();
        assertThat(match.getEntryUserList()).hasSize(1);
    }
}