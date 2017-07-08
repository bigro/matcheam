package matcheam.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class TestContext implements AutoCloseable {
	private Connection connection;
	private DSLContext dslContext;

	public Connection connection() {
		return connection;
	}

	public DSLContext dslContext() {
		return dslContext;
	}

	public TestContext() throws SQLException {
		this.connection = getConnect();
		this.dslContext = getDSLContext(connection);
	}

	private Connection getConnect() throws SQLException {
		String userName = "sa";
		String password = "";
		String url = "jdbc:h2:tcp://localhost:9092/matcheam;MODE=MySQL;";
		return DriverManager.getConnection(url, userName, password);
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
