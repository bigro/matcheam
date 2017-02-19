package matcheam;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by ooguro on 2017/01/07.
 */
@Service
public class GameService {

	// TODO 永続化する
	public HashMap<String, Game> gameMap = new HashMap<>();

	public void register(Game game) {
		//TODO 永続化する
		gameMap.put(game.getIdentifier().toString(), game);

	}
}
