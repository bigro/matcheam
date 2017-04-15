package matcheam.match;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
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
	private BigDecimal maxPlayers;
}
