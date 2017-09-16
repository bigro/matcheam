package matcheam.entry;

import matcheam.jooq.generate.tables.records.EntryRecord;
import matcheam.jooq.generate.tables.records.EntryUserRecord;
import matcheam.match.Identifier;
import matcheam.match.Match;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static matcheam.jooq.generate.Tables.ENTRY;
import static matcheam.jooq.generate.Tables.ENTRY_USER;
import static org.jooq.impl.DSL.trueCondition;

/**
 * Repositoryです。
 */
@Repository
public class EntryRepository {

    private DSLContext dsl;

    EntryRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    /**
     * １件のentryを登録します。
     *
     * @param entry 　Entryインスタンス
     * @throws Exception 登録が失敗した場合
     */
    void register(Entry entry) throws Exception {
        //TODO:本当の判断条件を入れる
        if (true) {
            dsl.insertInto(ENTRY)
                    .columns(ENTRY.MATCH_ID)
                    .values(entry.getMatch().getIdentifier().value())
                    .execute();
        }

        Identifier matchIdentifier = entry.getMatch().getIdentifier();
        dsl.insertInto(ENTRY_USER)
                .columns(ENTRY_USER.MATCH_ID, ENTRY_USER.ENTRY_USER_NAME)
                .values(matchIdentifier.value(), entry.getUserName())
                .execute();
    }

    /**
     * Matchで検索します。
     * <p>条件に一致しない場合、nullを返します。</p>
     *
     * @param match 募集
     * @return 応募者のリスト
     * @throws Exception 検索が失敗した場合
     */
    List<EntryUser> findEntryUserBy(Match match) {
        Condition condition = trueCondition();
        EntryRecord record = dsl.selectFrom(ENTRY)
                .where(condition.and(ENTRY.MATCH_ID.equal(match.getIdentifier().value())))
                .fetchOne();

        if (record == null) {
            return null;
        }

        Result<EntryUserRecord> entryUserRecords = getEntryUsers(record.getMatchId());
        return makeEntryUserList(entryUserRecords);
    }

    /**
     * EntryRecord のインスタンスから EntryUserのリスト のインスタンスを生成します。
     *
     * @param entryUserRecords Result<EntryUserRecord> のインスタンス
     * @return EntryUserのリスト
     */
    private List<EntryUser> makeEntryUserList(Result<EntryUserRecord> entryUserRecords) {
        List<EntryUser> entryUserList = new ArrayList<>();
        for (EntryUserRecord entryUserRecord : entryUserRecords) {
            String entryUserName = entryUserRecord.getEntryUserName();
            entryUserList.add(new EntryUser(entryUserName));
        }
        return entryUserList;
    }

    /**
     * EntryUserの検索結果を取得します。
     *
     * @param matchId 検索条件となるmatchId
     * @return EntryUserの検索結果
     */
    private Result<EntryUserRecord> getEntryUsers(int matchId) {
        Condition condition = trueCondition();
        return dsl.selectFrom(ENTRY_USER)
                .where(condition.and(ENTRY_USER.MATCH_ID.equal(matchId)))
                .fetch();
    }
}
