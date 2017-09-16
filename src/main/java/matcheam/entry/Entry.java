package matcheam.entry;

import java.util.List;

import matcheam.match.Identifier;
import matcheam.match.Match;

/**
 * 応募です。
 *
 * @since 1.0
 */
public class Entry {
    private Identifier identifier;
    private Match match;
    private String userName;

    public Entry() {
    }

    public Entry(Identifier identifier, Match match, String userName) {
        this(match, userName);
        this.identifier = identifier;
    }

    public Entry(Match match, String userName) {
        this.match = match;
        this.userName = userName;
    }

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

    public String getUserName() {
        return userName;
    }
}
