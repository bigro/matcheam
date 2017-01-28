package matcheam;

import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ooguro on 2017/01/07.
 */
@Service
public class MatchingService {

    // TODO 永続化する
    public HashMap<String, Matching> matchingMap = new HashMap<>();



    public void apply(EntryUser entryUser, Match match) {
        Matching matching = new Matching(match);
        for (Matching m : matchingMap.values()) {
            if (m.getMatch().equals(match)) {
                matching = m;
                break;
            }
        }
        matching.entry(entryUser);
        matchingMap.put(matching.getIdentifier().toString(), matching);
    }
}
