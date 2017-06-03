package matcheam.match;

import static matcheam.jooq.generate.tables.Match.MATCH;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matcheam.common.SystemContext;
import matcheam.common.exception.SystemException;

/**
 * 募集サービスです。
 *
 * @since 1.0
 */
@Service
public class MatchService {

	/**
	 * matchのリポジトリ
	 */
	MatchRepository repository;

	@Autowired
	DSLContext dsl;

	// TODO 永続化するまでの仮置き場
	public HashMap<String, Match> matchMap = new HashMap<>();

	/**
	 * コンストラクタです。
	 */
	public MatchService() {
		before();
	}

	/**
	 * コンストラクタです。
	 *
	 * @param repository matchのリポジトリ
	 */
	@Autowired
	public MatchService(MatchRepository repository) {
		this.repository = repository;
	}

	/**
	 * １件のmatchを登録します。
	 *
	 * @param match 募集
	 * @return 登録されたレコードの{@link Identifier}のインスタンス
	 */
	public Identifier register(Match match) {
		try {
			return repository.register(match);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * 主キーで検索します。
	 *
	 * @param identifier 募集の主キー
	 * @return 募集
	 */
	public Match findBy(Identifier identifier) {
		try {
			return repository.findBy(identifier);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public Collection<Match> findAll() {
		Result<Record> records = dsl.select().from(MATCH).fetch();
		List<Match> matches = new ArrayList<>();
		for (Record record : records) {
			Match match = new Match();
			match.setIdentifier(new Identifier(record.get(MATCH.IDENTIFIER)));
			match.setDate(record.get(MATCH.DATE));
			match.setTime(record.get(MATCH.TIME));
			match.setPlace(record.get(MATCH.PLACE));
			match.setMaxPlayers(record.get(MATCH.MAXPLAYERS));
			match.setLevel(Level.valueOf(record.get(MATCH.LEVEL)));
			matches.add(match);
		}
		return matches;
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
		Match match = new Match();
		match.setIdentifier(new Identifier(id));
		match.setPlace("場所");
		match.setDate(date);
		match.setTime("2時間");
		match.setMaxPlayers(BigDecimal.TEN);
		match.setLevel(level);
		return match;
	}
}
