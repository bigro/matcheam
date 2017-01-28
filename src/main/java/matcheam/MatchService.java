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
public class MatchService {

    // TODO 永続化する
    public HashMap<String, Match> matchMap = new HashMap<>();
    public HashMap<String, Matching> matchingMap = new HashMap<>();


    public MatchService() {
        before();
    }

    public void register(Match match) {
        // TODO 永続化する
        matchMap.put(match.getIdentifier().toString(), match);
    }

    public Match findOne(String id) {
        return matchMap.get(id);
    }

    public Collection<Match> findAll() {
        return matchMap.values();
    }

    public Collection<Match> findByLevel(Level... levels) {
        return findAll().stream().filter(m -> in(levels, m.getLevel())).collect(Collectors.toList());
    }

    public void apply(EntryUser entryUser, Match match) {
        Matching matching = new Matching(match);
        for (Matching m : matchingMap.values()) {
            if (m.getMatch().equals(match)) {
                matching = m;
                break;
            }
        }
        matching.entry(entryUser);
    }

    private boolean in(Level[] levels, Level level) {
        return Stream.of(levels).anyMatch(lvls -> lvls == level);
    }

    private void before() {
        register(createMatch("1", Level.LEVEL1));
        register(createMatch("2", Level.LEVEL1));
        register(createMatch("3", Level.LEVEL3));
        register(createMatch("4", Level.LEVEL3));
        register(createMatch("5", Level.LEVEL3));
        register(createMatch("6", Level.LEVEL4));
    }

    private Match createMatch(String id, Level level) {
        LocalDateTime date = LocalDateTime.now();
        Duration gameTime = Duration.ofHours(2);
        Match match = new Match();
        match.setIdentifier(new Identifier(id));
        match.setPlace("場所");
        match.setDate(date);
        match.setGameTime(gameTime);
        match.setMaxPlayers(10);
        match.setLevel(level);
        return match;
    }
}
