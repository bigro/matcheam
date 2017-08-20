package matcheam.entry;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import matcheam.entry.EntryUser;
import matcheam.match.Identifier;

/**
 * Created by ooguro on 2017/01/07.
 */
@Service
public class EntryUserService {

	// TODO 永続化する
	public HashMap<String, EntryUser> dummy = new HashMap<>();

	public void entry(EntryUser entryUser) {
		// TODO 永続化する
		dummy.put(entryUser.getIdentifier().toString(), entryUser);
	}

	public EntryUser findOne(Identifier identifier) {
		return dummy.get(identifier.toString());
	}

}
