package matcheam.common;

import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ConverterTest {

	@Test
	public void Java_sql_DateインスタンスをLocalDateインスタンスに変換できること() {
		Calendar calendar = Calendar.getInstance();
		Date parameter = new Date(calendar.getTime().getTime());
		LocalDate actual = Converter.toLocalDate(parameter);
		assertThat(actual).isEqualTo(LocalDate.now());
	}

	@Test
	public void LocalDateインスタンスをJava_sql_Dateインスタンスに変換できること() {
		LocalDate parameter = LocalDate.now();
		Date actual = Converter.toDate(parameter);
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND,0);
		calendar.set(Calendar.MILLISECOND,0);
		java.util.Date date = calendar.getTime();
		Date expected = new Date(date.getTime());

		assertThat(actual).isEqualTo(expected);
	}

}
