package matcheam;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
public class Match {

    Identifier identifier;

    String place;
    LocalDateTime date;
    Duration gameTime;
    Level level;
    long maxPlayers;

}
