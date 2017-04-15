package matcheam;

import java.util.UUID;


public class EntryUser {

    private Identifier identifier;

    public EntryUser() {
    }

    private String name;

    EntryUser(String name) {
        this.identifier = new Identifier(UUID.randomUUID().toString());
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public void setName(String name) {
        this.name = name;
    }
}
