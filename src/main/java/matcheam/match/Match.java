package matcheam.match;

import matcheam.entry.EntryUser;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 募集です。
 *
 * @since 1.0
 */
public class Match {

    private Identifier identifier;

    @NotEmpty
    private String place;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @NotEmpty
    private String start;
    @NotEmpty
    private String time;
    private Level level;
    @DecimalMax("99") //一旦99人までにする
    @NotNull
    private BigDecimal maxPlayers;
    private List<EntryUser> entryUserList;

    public Match() {
    }

    public Match(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String getPlace() {
        return place;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTime() {
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

    public void setTime(String time) {
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

    public static Match of(Identifier identifier) {
        return new Match(identifier);
    }

    public List<EntryUser> getEntryUserList() {
        return entryUserList;
    }

    public void setEntryUserList(List<EntryUser> entryUserList) {
        this.entryUserList = entryUserList;
    }
}
