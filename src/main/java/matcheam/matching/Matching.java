package matcheam.matching;

import lombok.Getter;
import matcheam.person.Person;
import matcheam.match.Identifier;
import matcheam.match.Match;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ooguro on 2017/01/28.
 */
@Getter
public class Matching {

	private Identifier identifier = new Identifier(UUID.randomUUID().toString());
	private Match match;
	private List<Person> entryUsers = new ArrayList<>();

	public Matching(Match match) {
		this.match = match;
	}

	public void entry(Person entryUser) {
		entryUsers.add(entryUser);
	}
}
