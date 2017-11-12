package matcheam.support;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.ninja_squad.dbsetup.destination.Destination;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class TestDataSource implements Destination {

    private Properties properties = new Properties();

    public DSLContext dslContext() throws SQLException {
        return getDSLContext(createConnection());
    }

    public TestDataSource() throws Exception {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        try (InputStream stream = loader.getResourceAsStream("application-local.properties")) {
            properties.load(stream);
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("spring.datasource.url"),
                properties.getProperty("spring.datasource.username"),
                properties.getProperty("spring.datasource.password")
        );
    }

    private DSLContext getDSLContext(Connection conn) {
        return DSL.using(conn, SQLDialect.MYSQL);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return createConnection();
    }
}
