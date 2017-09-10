package matcheam.entry;

import matcheam.jooq.generate.tables.records.EntryRecord;
import matcheam.match.Identifier;
import matcheam.match.Match;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static matcheam.jooq.generate.Tables.ENTRY;
import static matcheam.jooq.generate.Tables.ENTRY_USER;
import static org.jooq.impl.DSL.trueCondition;

@Repository
public class EntryRepository {
    public EntryRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    DSLContext dsl;

    /**
     * １件のentryを登録します。
     *
     * @param entry 　Entryインスタンス
     * @throws Exception 登録が失敗した場合
     */
    Entry register(Entry entry) throws Exception {
        int entryIdentifier;

        //TODO:本当の判断条件を入れる
        if (true) {
            EntryRecord entryRecord = dsl.insertInto(ENTRY)
                    .columns(ENTRY.MATCHID)
                    .values(entry.getMatch().getIdentifier().value())
                    .returning(ENTRY.IDENTIFIER)
                    .fetchOne();
            entryIdentifier = entryRecord.getIdentifier();
        }

        dsl.insertInto(ENTRY_USER)
                .columns(ENTRY_USER.ENTRYID, ENTRY_USER.ENTRYUSERNAME)
                .values(entryIdentifier, "nishida")
                .execute();

        entry.setIdentifier(Identifier.of(entryIdentifier));
        return entry;
    }



    /**
     * 主キーで検索します。
     * <p>条件に一致しない場合、nullを返します。</p>
     * @param entry 募集
     * @return 募集
     * @throws Exception 検索が失敗した場合
     */
    public Entry findBy(Entry entry) throws Exception {
        Condition condition = trueCondition();
        EntryRecord record = dsl.selectFrom(ENTRY)
                .where(condition.and(ENTRY.IDENTIFIER.equal(entry.getIdentifier().value())))
                .fetchOne();
        if (record == null) {
            return null;
        }
        return makeEntry(record);
    }


    /**
     * EntryRecord のインスタンスから Entry のインスタンスを生成します。
     * @param record　EntryRecord のインスタンス
     * @return　Entry のインスタンス
     */
    private Entry makeEntry(EntryRecord record) {
        return new Entry(Identifier.of(record.getIdentifier()), Match.of(Identifier.of(record.getMatchid())),null);
    }

}
