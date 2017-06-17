package matcheam.person;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import matcheam.match.Identifier;

/**
 * Created by ooguro on 2017/01/07.
 */
@Service
public class PersonService {

	// TODO 永続化する
	public HashMap<String, Person> personHashMap = new HashMap<>();

	public void entry(Person person) {
		// TODO 永続化する
		personHashMap.put(person.getIdentifier().toString(), person);
	}

	public Person findOne(Identifier identifier) {
		return personHashMap.get(identifier.toString());
	}

}
