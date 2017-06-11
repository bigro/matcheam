package matcheam.match;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * レベルの候補です。
 * @since 1.0
 *
 */
public class LevelCandidate {
	/**
	 * 検索時の候補とするレベルのリストを返します。
	 * @return レベルのリスト
	 */
	public static List<String> values() {
		List<String> values = new ArrayList<>();
		values.add("");
		values.addAll(
			Stream.of(Level.values())
				.map(Level::name)
				.collect(Collectors.toList()));
		return values;
	}
}
