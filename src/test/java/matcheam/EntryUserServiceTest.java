package matcheam;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by ooguro on 2017/01/07.
 */
public class EntryUserServiceTest {

    private EntryUserService entryUserService = new EntryUserService();

    @Test
    public void 登録した申込者を検索できること() throws Exception {
        EntryUser entryUser = new EntryUser();
        entryUser.setIdentifier(new Identifier("1"));
        entryUser.setName("名前");

        entryUserService.register(entryUser);

        EntryUser actual = entryUserService.entryUserHashMap.get("1");
        assertThat(actual.getName()).isEqualTo("名前");
        assertThat(actual.getIdentifier().toString()).isEqualTo("1");

    }
}