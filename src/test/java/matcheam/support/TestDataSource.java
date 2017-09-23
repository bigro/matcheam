package matcheam.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.ninja_squad.dbsetup.destination.DriverManagerDestination;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class TestDataSource implements AutoCloseable {
	private static final String TEST_DB_URL = "jdbc:mysql://localhost:3306/matcheam";
	private static final String TEST_DB_USER = "matcheam";
	private static final String TEST_DB_PASSWORD = "pass";
	private Connection connection;
	private DSLContext dslContext;

	public Connection connection() {
		return connection;
	}

	public DSLContext dslContext() {
		return dslContext;
	}

	public DriverManagerDestination driverManagerDestination() throws SQLException {
		return new DriverManagerDestination(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD);
	}

	public TestDataSource() throws SQLException {
		this.connection = getConnect();
		this.dslContext = getDSLContext(connection);
	}

	private Connection getConnect() throws SQLException {
		return DriverManager.getConnection(TEST_DB_URL, TEST_DB_USER, TEST_DB_PASSWORD);
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
