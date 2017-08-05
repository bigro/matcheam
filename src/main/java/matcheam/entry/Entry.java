package matcheam.entry;

import java.util.List;

import matcheam.match.Identifier;
import matcheam.match.Match;

/**
 * 応募です。
 * @since 1.0
 *
 */
public class Entry {
	private Identifier identifier;
	private Match match;
	private List<EntryUser> entryUserLsit;

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public List<EntryUser> getEntryUserLsit() {
		return entryUserLsit;
	}

	public void setEntryUserLsit(List<EntryUser> entryUserLsit) {
		this.entryUserLsit = entryUserLsit;
	}

}
