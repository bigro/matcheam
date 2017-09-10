package matcheam.entry;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.operation.Operation;
import matcheam.match.Identifier;
import matcheam.match.Match;
import matcheam.support.TestContext;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

public class EntryRepositoryTest {
    private TestContext testContext;

    EntryRepository sut;

    @Before
    public void setUp() throws Exception {
        testContext = new TestContext();
        sut = new EntryRepository(testContext.dslContext());
        Operation deleteAll = sequenceOf(
                deleteAllFrom("MATCHEAM.ENTRY_USER"),
                deleteAllFrom("MATCHEAM.ENTRY")
        );
        DbSetup dbSetup = new DbSetup(testContext.driverManagerDestination(), deleteAll);
        dbSetup.launch();
    }

    @Test
    public void 応募を登録して検索できること() throws Exception {
        Match match = Match.of(Identifier.of(2));
        Entry param = sut.register(new Entry(null, match, Arrays.asList(new EntryUser("nishida"))));

        Entry actual = sut.findBy(param);

        assertThat(actual).isNotNull();
        assertThat(actual.getEntryUserList()).hasSize(1);
    }
}