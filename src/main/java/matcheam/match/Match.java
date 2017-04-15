package matcheam.match;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class Match {

    private Identifier identifier;

    private String place;
    private LocalDateTime date;
    private Duration gameTime;
    private Level level;
    private BigDecimal maxPlayers;

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getPlace() {
        return place;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Duration getGameTime() {
        return gameTime;
    }

    public Level getLevel() {
        return level;
    }

    public BigDecimal getMaxPlayers() {
        return maxPlayers;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setGameTime(Duration gameTime) {
        this.gameTime = gameTime;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setMaxPlayers(BigDecimal maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
