package matcheam;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

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

	public Collection<Match> findAll() {
		return matchMap.values();
	}

	public Collection<Match> findByLevel(Level level) {
		return matchMap.values().stream().filter(m -> m.getLevel() == level).collect(Collectors.toList());
	}
}
