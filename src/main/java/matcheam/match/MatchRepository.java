package matcheam.match;

import static matcheam.jooq.generate.Tables.MATCH;
import static org.jooq.impl.DSL.trueCondition;

import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import matcheam.common.SystemContext;
import matcheam.jooq.generate.tables.records.MatchRecord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * matchのリポジトリです。
 * @since 1.0
 */
@Repository
public class MatchRepository {

	DSLContext dsl;

	public MatchRepository(DSLContext dsl) {
			this.dsl = dsl;
	}

	/**
	 * １件のmatchを登録します。
	 * @param match　Matchインスタンス
	 * @return identifier
	 * @throws Exception 登録が失敗した場合
	 */
	Identifier register(Match match) throws Exception {
		MatchRecord matchRecord = dsl.insertInto(MATCH)
			.columns(MATCH.PLACE, MATCH.DATE, MATCH.START, MATCH.TIME, MATCH.LEVEL, MATCH.MAXPLAYERS)
			.values(match.getPlace(), match.getDate(), match.getStart(), match.getTime(), match.getLevel().name(),
				match.getMaxPlayers())
			.returning(MATCH.IDENTIFIER)
			.fetchOne();

		return new Identifier(matchRecord.getValue(MATCH.IDENTIFIER));
	}

	/**
	 * 主キーで検索します。
	 * <p>条件に一致しない場合、nullを返します。</p>
	 * @param identifier 募集の主キー
	 * @return 募集
	 * @throws Exception 検索が失敗した場合
	 */
	Match findBy(Identifier identifier) throws Exception {
		Condition condition = trueCondition();
		MatchRecord record = dsl.selectFrom(MATCH)
			.where(condition.and(MATCH.IDENTIFIER.equal(identifier.toString())))
			.fetchOne();
		if (record == null) {
			return null;
		}
		return toMatch(record);
	}

	/**
	 * MatchRecord のインスタンスから Match のインスタンスを生成します。
	 * @param record　MatchRecord のインスタンス
	 * @return　Match のインスタンス
	 */
	private Match toMatch(MatchRecord record) {
		Match match = new Match();
		match.setDate(record.getValue(MATCH.DATE));
		match.setLevel(Level.valueOf(record.getValue(MATCH.LEVEL)));
		match.setMaxPlayers(record.getValue(MATCH.MAXPLAYERS));
		match.setPlace(record.getValue(MATCH.PLACE));
		match.setStart(record.getValue(MATCH.START));
		match.setTime(record.getValue(MATCH.TIME));
		match.setIdentifier(new Identifier(record.getValue(MATCH.IDENTIFIER)));
		return match;
	}

	public Collection<Match> findAll() {
		Result<Record> records = dsl.select().from(matcheam.jooq.generate.tables.Match.MATCH).fetch();
		List<Match> matches = new ArrayList<>();
		for (Record record : records) {
			Match match = new Match();
			match.setIdentifier(new Identifier(record.get(matcheam.jooq.generate.tables.Match.MATCH.IDENTIFIER)));
			match.setDate(record.get(matcheam.jooq.generate.tables.Match.MATCH.DATE));
			match.setStart(record.get(matcheam.jooq.generate.tables.Match.MATCH.START));
			match.setTime(record.get(matcheam.jooq.generate.tables.Match.MATCH.TIME));
			match.setPlace(record.get(matcheam.jooq.generate.tables.Match.MATCH.PLACE));
			match.setMaxPlayers(record.get(matcheam.jooq.generate.tables.Match.MATCH.MAXPLAYERS));
			match.setLevel(Level.valueOf(record.get(matcheam.jooq.generate.tables.Match.MATCH.LEVEL)));
			matches.add(match);
		}
		return matches;
	}

	public Collection<Match> findBy(Level... levels) {
		return findAll().stream().filter(m -> in(levels, m.getLevel())).collect(Collectors.toList());
	}

	private boolean in(Level[] levels, Level level) {
		return Stream.of(levels).anyMatch(lvls -> lvls == level);
	}
}
