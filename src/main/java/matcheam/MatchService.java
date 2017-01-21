package matcheam;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

/**
 * Created by ooguro on 2017/01/07.
 */
@Service
public class MatchService {

	// TODO 永続化する
	public HashMap<String, Match> matchMap = new HashMap<>();

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

	private boolean in(Level[] levels, Level level) {
		return Stream.of(levels).anyMatch(lvls -> lvls == level);
	}
}
