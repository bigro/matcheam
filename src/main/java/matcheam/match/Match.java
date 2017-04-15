package matcheam.match;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

public class Match {

    private Identifier identifier;

    private String place;
    private LocalDate date;
    private String start;
    private Duration time;
    private Level level;
    private BigDecimal maxPlayers;

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getPlace() {
        return place;
    }

    public LocalDate getDate() {
        return date;
    }

    public Duration getTime() {
        return time;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setTime(Duration time) {
        this.time = time;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setMaxPlayers(BigDecimal maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
