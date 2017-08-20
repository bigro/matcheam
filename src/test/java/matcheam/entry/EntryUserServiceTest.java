package matcheam.entry;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Test;

import matcheam.match.Identifier;
import matcheam.person.PersonService;

/**
 * Created by ooguro on 2017/01/07.
 */
public class EntryUserServiceTest {

	private PersonService entryUserService = new PersonService();

	@Test
	public void 登録した申込者を取得できること() throws Exception {
		EntryUser entryUser = new EntryUser();
		entryUser.setIdentifier(new Identifier("1"));
		entryUser.setEntryUserName("名前");

		entryUserService.entry(entryUser);

		EntryUser actual = entryUserService.dummy.get("1");
		assertThat(actual.getEntryUserName()).isEqualTo("名前");
		assertThat(actual.getIdentifier().toString()).isEqualTo("1");
	}
}
