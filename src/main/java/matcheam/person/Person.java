package matcheam.person;

import lombok.Data;
import lombok.NoArgsConstructor;
import matcheam.match.Identifier;
import sun.security.provider.DSAPublicKeyImpl;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Person {

	private Identifier identifier;

	private String name;

	public Person(String name) {
		this.identifier = new Identifier(UUID.randomUUID().toString());
		this.name = name;
	}
}
