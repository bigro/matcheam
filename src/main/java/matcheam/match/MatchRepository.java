package matcheam.match;

import matcheam.common.Converter;
import matcheam.common.SystemContext;
import matcheam.jooq.generate.tables.records.MatchRecord;

import org.jooq.Condition;
import org.springframework.stereotype.Repository;
import static org.jooq.impl.DSL.trueCondition;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static matcheam.jooq.generate.Tables.MATCH;

/**
 * matchのリポジトリです。
 * @since 1.0
 */
@Repository
public class MatchRepository {
	/**
	 * １件のmatchを登録します。
	 * @param match　Matchインスタンス
	 * @return identifier
	 * @throws Exception 登録が失敗した場合
	 */
	Identifier register(Match match) throws Exception {
		try (SystemContext sc = new SystemContext()) {
			MatchRecord matchRecord = sc.dslContext().insertInto(MATCH)
				.columns(MATCH.PLACE, MATCH.DATE, MATCH.START, MATCH.TIME, MATCH.LEVEL, MATCH.MAXPLAYERS)
				//TODO valuesの引数nullを修正
				.values(match.getPlace(), null, match.getStart(), match.getTime(), match.getLevel().name(),
					match.getMaxPlayers())
				.returning(MATCH.IDENTIFIER)
				.fetchOne();

			return new Identifier(matchRecord.getValue(MATCH.IDENTIFIER));
		}
	}

	/**
	 * 主キーで検索します。
	 * <p>条件に一致しない場合、nullを返します。</p>
	 * @param identifier 募集の主キー
	 * @return 募集
	 * @throws Exception 検索が失敗した場合
	 */
	Match findBy(Identifier identifier) throws Exception {
		try (SystemContext sc = new SystemContext()) {
			Condition condition = trueCondition();
			MatchRecord record = sc.dslContext().selectFrom(MATCH)
				.where(condition.and(MATCH.IDENTIFIER.equal(identifier.toString())))
				.fetchOne();
			if (record == null) {
				return null;
			}
			Match match = new Match();
			Date date = record.getValue(MATCH.DATE);
			LocalDate localDate = Converter.toLocalDate(date);
			match.setDate(localDate);
			match.setLevel(Level.valueOf(record.getValue(MATCH.LEVEL)));
			match.setMaxPlayers(record.getValue(MATCH.MAXPLAYERS));
			match.setPlace(record.getValue(MATCH.PLACE));
			match.setStart(record.getValue(MATCH.START));
			match.setTime(record.getValue(MATCH.TIME));
			match.setIdentifier(new Identifier(record.getValue(MATCH.IDENTIFIER)));
			return match;
		}
	}

	
}
