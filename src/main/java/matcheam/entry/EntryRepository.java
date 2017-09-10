package matcheam.entry;

import matcheam.jooq.generate.tables.records.EntryRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static matcheam.jooq.generate.Tables.ENTRY;
import static matcheam.jooq.generate.Tables.ENTRY_USER;

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
    void register(Entry entry) throws Exception {
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

    }
}
