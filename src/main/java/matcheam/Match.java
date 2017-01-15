package matcheam;

import java.time.Duration;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Match {

	String place;
	LocalDateTime date;
	Duration gameTime;
	Level level;
	long maxPlayers;
	
}
