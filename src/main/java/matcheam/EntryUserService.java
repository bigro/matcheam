package matcheam;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by ooguro on 2017/01/07.
 */
@Service
public class EntryUserService {

	// TODO 永続化する
	public HashMap<String, EntryUser> entryUserHashMap = new HashMap<>();

	public void register(EntryUser entryUser) {
		// TODO 永続化する
		entryUserHashMap.put(entryUser.getIdentifier().toString(), entryUser);
	}

	public EntryUser findOne(Identifier identifier) {
		return entryUserHashMap.get(identifier.toString());
	}
}
