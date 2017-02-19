package matcheam;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * Created by ooguro on 2017/01/07.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Game {

	private Identifier identifier = new Identifier();

	private Place place = new Place();

	private StartTime startTime = new StartTime();

	private GameTime gameTime = new GameTime();

	private Enemy enemy = new Enemy();

}
