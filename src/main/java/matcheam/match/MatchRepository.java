package matcheam.match;

import matcheam.common.SystemContext;
import matcheam.jooq.generate.tables.records.MatchRecord;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

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
                    .values(match.getPlace(), null, match.getStart(), match.getTime(), match.getLevel().name(), match.getMaxPlayers())
                    .returning(MATCH.IDENTIFIER)
                    .fetchOne();

            return new Identifier(matchRecord.getValue(MATCH.IDENTIFIER));
        }
    }

    /**
     * 主キーで検索します。
     * @param identifier 募集の主キー
     * @return 募集
     */
     Match findBy(Identifier identifier) throws Exception {
         try (SystemContext sc = new SystemContext()) {
             MatchRecord matchRecord = sc.dslContext().selectFrom(MATCH).where(MATCH.IDENTIFIER.getName())

             return new Identifier(matchRecord.getValue(MATCH.IDENTIFIER));
         }
        return null;
    }
}
