package matcheam;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Test;

import matcheam.match.Identifier;
import matcheam.person.Person;
import matcheam.person.PersonService;

/**
 * Created by ooguro on 2017/01/07.
 */
public class EntryUserServiceTest {

	private PersonService entryUserService = new PersonService();

	@Test
	public void 登録した申込者を検索できること() throws Exception {
		Person entryUser = new Person();
		entryUser.setIdentifier(new Identifier("1"));
		entryUser.setName("名前");

		entryUserService.entry(entryUser);

		Person actual = entryUserService.personHashMap.get("1");
		assertThat(actual.getName()).isEqualTo("名前");
		assertThat(actual.getIdentifier().toString()).isEqualTo("1");
	}
}
