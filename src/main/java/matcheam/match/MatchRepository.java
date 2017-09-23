package matcheam.match;

import static matcheam.jooq.generate.Tables.ENTRY_USER;
import static matcheam.jooq.generate.Tables.MATCH;
import static org.jooq.impl.DSL.trueCondition;

import matcheam.entry.EntryUser;
import matcheam.jooq.generate.tables.records.EntryUserRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import matcheam.jooq.generate.tables.records.MatchRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * matchのリポジトリです。
 *
 * @since 1.0
 */
@Repository
public class MatchRepository {

    private DSLContext dsl;

    public MatchRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    /**
     * １件のmatchを登録します。
     *
     * @param match 　Matchインスタンス
     * @return identifier
     * @throws Exception 登録が失敗した場合
     */
    Identifier register(Match match) throws Exception {
        MatchRecord matchRecord = dsl.insertInto(MATCH)
                .columns(MATCH.PLACE, MATCH.DATE, MATCH.START, MATCH.TIME, MATCH.LEVEL, MATCH.MAX_PLAYERS)
                .values(match.getPlace(), match.getDate(), match.getStart(), match.getTime(), match.getLevel().name(),
                        match.getMaxPlayers())
                .returning(MATCH.IDENTIFIER)
                .fetchOne();

        return new Identifier(matchRecord.getValue(MATCH.IDENTIFIER));
    }

    /**
     * 主キーで検索します。
     * <p>条件に一致しない場合、nullを返します。</p>
     *
     * @param identifier 募集の主キー
     * @return 募集
     * @throws Exception 検索が失敗した場合
     */
    Match findBy(Identifier identifier) throws Exception {
        MatchRecord record = dsl.selectFrom(MATCH)
                .where(trueCondition().and(MATCH.IDENTIFIER.equal(identifier.value())))
                .fetchOne();
        if (record == null) {
            return null;
        }
        Result<EntryUserRecord> entryUserRecords = findEntryUserBy(identifier);

        return makeMatch(record, entryUserRecords);
    }

    private Result<EntryUserRecord> findEntryUserBy(Identifier matchId) {
        return dsl.selectFrom(ENTRY_USER)
                    .where(trueCondition().and(ENTRY_USER.MATCH_ID.equal(matchId.value())))
                    .fetch();
    }

    /**
     * 検索して全件返します。
     *
     * @return 募集のリスト
     */
    List<Match> findAll() {
        Result<MatchRecord> records = dsl.selectFrom(MATCH).fetch();
        return makeMatches(records);
    }

    List<Match> findBy(Level... levels) {
        return findAll().stream().filter(m -> in(levels, m.getLevel())).collect(Collectors.toList());
    }

    /**
     * MatchRecord のインスタンスから Match のインスタンスを生成します。
     *
     * @param record 　MatchRecord のインスタンス
     * @param entryUserRecords
     * @return　Match のインスタンス
     */
    private Match makeMatch(MatchRecord record, Result<EntryUserRecord> entryUserRecords) {
        Match match = new Match();
        match.setIdentifier(new Identifier(record.get(MATCH.IDENTIFIER)));
        match.setDate(record.get(MATCH.DATE));
        match.setStart(record.get(MATCH.START));
        match.setTime(record.get(MATCH.TIME));
        match.setPlace(record.get(MATCH.PLACE));
        match.setMaxPlayers(record.get(MATCH.MAX_PLAYERS));
        match.setLevel(Level.valueOf(record.get(MATCH.LEVEL)));

        List<EntryUser> entryUsers = new ArrayList<>();
        for (EntryUserRecord entryUserRecord : entryUserRecords) {
            EntryUser entryUser = new EntryUser(new Identifier(entryUserRecord.get(ENTRY_USER.IDENTIFIER)),
                    entryUserRecord.get(ENTRY_USER.ENTRY_USER_NAME));
            entryUsers.add(entryUser);
        }
        match.setEntryUserList(entryUsers);
        return match;
    }

    /**
     * jooqの検索結果から募集のリストを作成します。
     *
     * @param records jooqの検索結果
     * @return 募集のリスト
     */
    private List<Match> makeMatches(Result<MatchRecord> records) {
        List<Match> matches = new ArrayList<>();
        for (MatchRecord record : records) {
            Result<EntryUserRecord> entryUserRecords = findEntryUserBy(new Identifier(record.get(MATCH.IDENTIFIER)));
            matches.add(makeMatch(record, entryUserRecords));
        }
        return matches;
    }

    private boolean in(Level[] levels, Level level) {
        return Stream.of(levels).anyMatch(lvls -> lvls == level);
    }
}
