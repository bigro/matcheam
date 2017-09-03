package matcheam.matching;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import matcheam.entry.EntryUser;
import matcheam.match.Identifier;
import matcheam.match.Match;

/**
 * Created by ooguro on 2017/01/28.
 */
public class Matching {

	private Identifier identifier = new Identifier(1);
	private Match match;
	private List<EntryUser> entryUserList = new ArrayList<>();

    public Matching(Match match) {
        this.match = match;
    }


    public void entry(EntryUser entryUser) {
		entryUserList.add(entryUser);
	}

    public Match getMatch() {
        return match;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public List<EntryUser> getEntryUserList() {
        return entryUserList;
    }
}
