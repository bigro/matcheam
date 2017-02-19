package matcheam;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Created by ooguro on 2017/01/07.
 */
public class GameServiceTest {

	private GameService gameService = new GameService();

	@Test
	public void 試合内容を登録できること() throws Exception {
		LocalDateTime startTime = LocalDateTime.now();
		LocalTime gameTime = LocalTime.of(2, 0);
		Game game = new Game(
			new Identifier("1"),
			new Place("場所"),
			new StartTime(startTime),
			new GameTime(gameTime),
			new Enemy("対戦相手"));

		gameService.register(game);

		Game actual = gameService.gameMap.get("1");
		SoftAssertions softAssertions = new SoftAssertions();
		softAssertions.assertThat(actual.getIdentifier().toString()).isEqualTo("1");
		softAssertions.assertThat(actual.getPlace().toString()).isEqualTo("場所");
		softAssertions.assertThat(actual.getStartTime().getValue()).isEqualTo(startTime);
		softAssertions.assertThat(actual.getGameTime().getValue()).isEqualTo(gameTime);
		softAssertions.assertAll();
	}
}
