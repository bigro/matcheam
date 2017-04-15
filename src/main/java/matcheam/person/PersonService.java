package matcheam.person;

import matcheam.match.Identifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by ooguro on 2017/01/07.
 */
@Service
public class PersonService {

	// TODO 永続化する
	public HashMap<String, Person> entryUserHashMap = new HashMap<>();

	public void entry(Person entryUser) {
		// TODO 永続化する
		entryUserHashMap.put(entryUser.getIdentifier().toString(), entryUser);
	}

	public Person findOne(Identifier identifier) {
		return entryUserHashMap.get(identifier.toString());
	}
}
