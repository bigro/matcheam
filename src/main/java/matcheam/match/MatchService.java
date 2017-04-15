package matcheam.match;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static matcheam.jooq.generate.tables.Match.MATCH;

/**
 * Created by ooguro on 2017/01/07.
 */
@Service
public class MatchService {

	// TODO 永続化する
	public HashMap<String, Match> matchMap = new HashMap<>();

	public MatchService() {
		before();
	}

	public void register(Match match) {
		// TODO 永続化する
		matchMap.put(match.getIdentifier().toString(), match);
	}

	public Match findOne(String id) {
		return matchMap.get(id);
	}

	public Collection<Match> findAll() {
		String userName = "sa";
		String password = "";
		String url = "jdbc:h2:file:./.data/h2/db;";

		try (Connection conn = DriverManager.getConnection(url, userName, password)) {
			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
			Result<Record> records = create.select().from(MATCH).fetch();
			List<Match> matches = new ArrayList<>();
			for (Record record : records) {
				Match match = new Match();
				match.setIdentifier(new Identifier(record.get(MATCH.IDENTIFIER)));
				//TODO DATE型の取り方わからない
				match.setDate(LocalDate.now());
				match.setTime(Duration.ofHours(2L));
				match.setPlace(record.get(MATCH.PLACE));
				match.setMaxPlayers(record.get(MATCH.MAXPLAYERS));
				match.setLevel(Level.valueOf(record.get(MATCH.LEVEL)));
				matches.add(match);
			}
			return matches;
		}
		// For the sake of this tutorial, let's keep exception handling simple
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Collection<Match> findByLevel(Level... levels) {
		return findAll().stream().filter(m -> in(levels, m.getLevel())).collect(Collectors.toList());
	}

	private boolean in(Level[] levels, Level level) {
		return Stream.of(levels).anyMatch(lvls -> lvls == level);
	}

	private void before() {
		register(createMatch("1", Level.LEVEL1));
		register(createMatch("2", Level.LEVEL1));
		register(createMatch("3", Level.LEVEL3));
		register(createMatch("4", Level.LEVEL3));
		register(createMatch("5", Level.LEVEL3));
		register(createMatch("6", Level.LEVEL4));
	}

	private Match createMatch(String id, Level level) {
		LocalDate date = LocalDate.now();
		Duration gameTime = Duration.ofHours(2);
		Match match = new Match();
		match.setIdentifier(new Identifier(id));
		match.setPlace("場所");
		match.setDate(date);
		match.setTime(gameTime);
		match.setMaxPlayers(BigDecimal.TEN);
		match.setLevel(level);
		return match;
	}
}
