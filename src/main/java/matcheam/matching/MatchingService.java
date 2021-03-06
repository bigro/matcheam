package matcheam.matching;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import matcheam.entry.EntryUser;
import matcheam.match.Match;

/**
 * Created by ooguro on 2017/01/07.
 */
@Service
public class MatchingService {

	// TODO 永続化する
	public HashMap<String, Matching> matchingMap = new HashMap<>();

	public void matching(Match match, EntryUser entryUser) {
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

	public Matching get(Match match) {
		for (Matching matching : matchingMap.values()) {
			if (matching.getMatch().equals(match)) {
				return matching;
			}
		}
		return null;
		// TODO こうすべきか？
		// throw new NoSuchElementException("Matching exists, but match no exists");
	}
}
