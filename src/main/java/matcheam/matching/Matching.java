package matcheam.matching;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import matcheam.match.Identifier;
import matcheam.match.Match;
import matcheam.person.Person;

/**
 * Created by ooguro on 2017/01/28.
 */
public class Matching {

	private Identifier identifier = new Identifier(UUID.randomUUID().toString());
	private Match match;
	private List<Person> persons = new ArrayList<>();

    public Matching(Match match) {
        this.match = match;
    }


    public void entry(Person person) {
		persons.add(person);
	}

    public Match getMatch() {
        return match;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public List<Person> getPersons() {
        return persons;
    }
}
