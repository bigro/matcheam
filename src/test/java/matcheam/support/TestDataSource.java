package matcheam.support;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class TestDataSource implements AutoCloseable {
    private String testDbUrl;
    private String testDbUser;
    private String testDbPassword;
    private Connection connection;
    private DSLContext dslContext;

    public Connection connection() {
        return connection;
    }

    public DSLContext dslContext() {
        return dslContext;
    }

    public DriverManagerDestination driverManagerDestination() throws SQLException {
        return new DriverManagerDestination(testDbUrl, testDbUser, testDbPassword);
    }

    public TestDataSource() throws Exception {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        try (InputStream stream = loader.getResourceAsStream("application-local.properties")) {
            Properties properties = new Properties();
            properties.load(stream);
            testDbUrl = properties.getProperty("spring.datasource.url");
            testDbUser = properties.getProperty("spring.datasource.username");
            testDbPassword = properties.getProperty("spring.datasource.password");
        }
        this.connection = getConnect();
        this.dslContext = getDSLContext(connection);
    }

    private Connection getConnect() throws SQLException {
        return DriverManager.getConnection(testDbUrl, testDbUser, testDbPassword);
    }

    private DSLContext getDSLContext(Connection conn) {
        return DSL.using(conn, SQLDialect.MYSQL);
    }

    @Override
    public void close() throws Exception {
        dslContext.close();
        connection.close();
    }
}
