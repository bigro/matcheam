package matcheam.common;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * システム全体のコンテキストです。
 * @since 1.0
 */
public class SystemContext  implements AutoCloseable {
    private Connection connection;
    private DSLContext dslContext;

    /**
     * DSLContextのインスタンスを取得します。
     *
     * @return DSLContextのインスタンス
     */
    public DSLContext dslContext() {
        return dslContext;
    }

    /**
     * コンストラクタです。
     *
     * @throws SQLException データアクセスに異常があった場合
     */
    public SystemContext() throws SQLException {
        Connection connection = getConnect();
        this.dslContext = getDSLContext(connection);
    }

    /**
     * Connectionのインスタンスを取得します。
     *
     * @return Connectionのインスタンス
     * @throws SQLException データアクセスに異常があった場合
     */
    private Connection getConnect() throws SQLException {
        String userName = "sa";
        String password = "";
        String url = "jdbc:h2:tcp://localhost:9092/matcheam;MODE=MySQL;";
        return DriverManager.getConnection(url, userName, password);
    }

    /**
     * ConnectionのインスタンスからDSLContextのインスタンスを生成します。
     *
     * @return DSLContextのインスタンス
     */
    private DSLContext getDSLContext(Connection conn) {
        return DSL.using(conn, SQLDialect.MYSQL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        dslContext.close();
        connection.close();
    }
}
