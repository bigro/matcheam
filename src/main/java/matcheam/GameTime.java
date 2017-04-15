package matcheam;

import java.time.LocalTime;

/**
 * Created by ooguro on 2017/01/07.
 */
public class GameTime {
    private LocalTime value;

    public GameTime(LocalTime gameTime) {
        this.value = gameTime;
    }

    public GameTime() {
    }

    public LocalTime getValue() {
        return value;
    }
}
