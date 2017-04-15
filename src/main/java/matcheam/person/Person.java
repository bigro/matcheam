package matcheam.person;

import matcheam.match.Identifier;
import sun.security.provider.DSAPublicKeyImpl;

import java.util.UUID;

public class Person {

    private Identifier identifier;

    private String name;

    public Person() {
    }

    public Person(String name) {
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
