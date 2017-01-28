package matcheam;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ooguro on 2017/01/07.
 */
public class MatchingServiceTest {

    private MatchingService matchingService = new MatchingService();

    @Test
    public void 募集に対して応募できること() throws Exception {
        Match match = match(new Identifier("1"));
        EntryUser entryUser = new EntryUser();
        entryUser.setIdentifier(new Identifier("1"));
        entryUser.setName("名前");
        matchingService.apply(entryUser, match);

        Matching actual = matchingService.findOne(match);
        assertThat(actual.getMatch()).isEqualTo(match);
        assertThat(actual.getEntryUsers())
                .extracting(EntryUser::getName)
                .contains("名前");
    }

    private Match match(Identifier identifier) {
        Match match = new Match();
        match.setIdentifier(identifier);
        match.setPlace("場所");
        match.setDate(LocalDateTime.of(2017, 01, 25, 1, 0));
        match.setGameTime(Duration.ofHours(2));
        match.setMaxPlayers(10);
        return match;
    }
}