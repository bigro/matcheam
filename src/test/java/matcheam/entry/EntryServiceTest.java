package matcheam.entry;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Test;

import matcheam.match.Identifier;

/**
 * Created by ooguro on 2017/01/07.
 */
public class EntryServiceTest {

	private EntryService entryService = new EntryService();

	@Test
	public void 登録した申込者を取得できること() throws Exception {
		EntryUser entryUser = new EntryUser();
		entryUser.setIdentifier(new Identifier("1"));
		entryUser.setEntryUserName("名前");

		entryService.entry(entryUser);

		EntryUser actual = entryService.dummy.get("1");
		assertThat(actual.getEntryUserName()).isEqualTo("名前");
		assertThat(actual.getIdentifier().toString()).isEqualTo("1");
	}
}
