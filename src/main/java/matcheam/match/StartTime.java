package matcheam.match;

import java.time.LocalDateTime;

/**
 * Created by ooguro on 2017/01/07.
 */
public class StartTime {

    private LocalDateTime value;

    public StartTime(LocalDateTime startTime) {
        this.value = startTime;
    }

    public StartTime() {
    }

    public LocalDateTime getValue() {
        return value;
    }
}
