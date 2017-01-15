package matcheam;

import org.springframework.stereotype.Service;

import java.util.HashMap;

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
}
