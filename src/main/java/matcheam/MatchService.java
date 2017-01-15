package matcheam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.springframework.stereotype.Service;

/**
 * Created by ooguro on 2017/01/07.
 */
@Service
public class MatchService {

    // TODO 永続化する
    public HashMap<String, Match> matchMap = new HashMap<>();

    public void register(Match match) {
        //TODO 永続化する
        matchMap.put(match.getIdentifier().toString(), match);

    }
    
    public Collection<Match> findAll(){
    	return matchMap.values();
    }
    
    public Collection<Match> findByLevel(Level level) {
    	Collection<Match> result = new ArrayList<>();
		for (Match match : matchMap.values()) {
			if (level == match.getLevel()) {
				result.add(match);	
			}
		}
		return result;
	}
}
