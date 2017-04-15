package matcheam.matching;

import matcheam.person.Person;
import matcheam.match.Match;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by ooguro on 2017/01/07.
 */
@Service
public class MatchingService {

	// TODO 永続化する
	public HashMap<String, Matching> matchingMap = new HashMap<>();

	public void matching(Match match, Person entryUser) {
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
