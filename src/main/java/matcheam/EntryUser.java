package matcheam;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class EntryUser {

    private Identifier identifier;

    private String name;

    EntryUser(String name) {
        this.identifier = new Identifier(UUID.randomUUID().toString());
        this.name = name;
    }
}