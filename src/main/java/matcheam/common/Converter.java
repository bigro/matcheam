package matcheam.common;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 型変換を行うコンバーターです。
 * @since 1.0
 */
public final class Converter {
	/**
	 * コンストラクタです。
	 */
	private Converter() {
	}

	/**
	 * {@link java.sql.Date}インスタンスを{@link LocalDate}インスタンスに変換します。
	 * @param date {@link java.sql.Date}インスタンス
	 * @return {@link LocalDate}インスタンス
	 */
	public static LocalDate toLocalDate(Date date) {
		java.util.Date d = new java.util.Date(date.getTime());
		return LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault()).toLocalDate();
	}
}
