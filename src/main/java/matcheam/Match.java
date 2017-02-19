package matcheam;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class Match {

	private Identifier identifier;

	private String place;
	private LocalDateTime date;
	private Duration gameTime;
	private Level level;
	private long maxPlayers;
}
