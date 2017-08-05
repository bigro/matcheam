package matcheam.entry;

import matcheam.match.Identifier;

/**
 * 応募者です。
 * @since 1.0
 *
 */
public class EntryUser {
	private Identifier identifier;
	private String entryUserName;

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public String getEntryUserName() {
		return entryUserName;
	}

	public void setEntryUserName(String entryUserName) {
		this.entryUserName = entryUserName;
	}

}
