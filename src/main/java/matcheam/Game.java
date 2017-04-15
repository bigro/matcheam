package matcheam;

/**
 * Created by ooguro on 2017/01/07.
 */
public class Game {

    private Identifier identifier = new Identifier();

    private Place place = new Place();

    private StartTime startTime = new StartTime();

    private GameTime gameTime = new GameTime();

    private Enemy enemy = new Enemy();

    public Identifier getIdentifier() {
        return identifier;
    }

    public Game(Identifier identifier, Place place, StartTime startTime, GameTime gameTime, Enemy enemy) {
        this.identifier = identifier;
        this.place = place;
        this.startTime = startTime;
        this.gameTime = gameTime;
        this.enemy = enemy;
    }

    public Place getPlace() {
        return place;
    }

    public StartTime getStartTime() {
        return startTime;
    }

    public GameTime getGameTime() {
        return gameTime;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
