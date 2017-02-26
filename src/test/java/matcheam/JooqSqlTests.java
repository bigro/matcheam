package matcheam;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;

import static matcheam.jooq.generate.Tables.MATCH;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JooqSqlTests {

	@Test
	public void insertSelect() {
		String userName = "sa";
		String password = "";
		String url = "jdbc:h2:file:./.data/h2/db;";

		try (Connection conn = DriverManager.getConnection(url, userName, password)) {
			DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
			Result<Record> result = create.select().from(MATCH).fetch();
			for (Record r : result) {
				String id = r.getValue(MATCH.IDENTIFIER);
				String name = r.getValue(MATCH.PLACE);

				System.out.println("ID: " + id + " place: " + name);
			}
		}
		// For the sake of this tutorial, let's keep exception handling simple
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
