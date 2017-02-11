package matcheam;

import lombok.Getter;

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
    private List<EntryUser> entryUsers = new ArrayList<>();

    public Matching(Match match) {
        this.match = match;
    }

    public void entry(EntryUser entryUser) {
        entryUsers.add(entryUser);
    }
}
