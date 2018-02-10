package matcheam.match;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matcheam.common.exception.SystemException;

/**
 * 募集サービスです。
 *
 * @since 1.0
 */
@Service
public class MatchService {

	/**
	 * matchのリポジトリ
	 */
	private MatchRepository repository;

	// TODO 永続化するまでの仮置き場
	public HashMap<String, Match> matchMap = new HashMap<>();

	/**
	 * コンストラクタです。
	 */
	public MatchService() {
		before();
	}

	/**
	 * コンストラクタです。
	 *
	 * @param repository matchのリポジトリ
	 */
	@Autowired
	public MatchService(MatchRepository repository) {
		this.repository = repository;
	}

	/**
	 * １件のmatchを登録します。
	 *
	 * @param match 募集
	 * @return 登録されたレコードの{@link Identifier}のインスタンス
	 */
	public Identifier register(Match match) {
		try {
			return repository.register(match);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * 主キーで検索します。
	 *
	 * @param identifier 募集の主キー
	 * @return 募集
	 */
	public Match findBy(Identifier identifier) {
		try {
			return repository.findBy(identifier);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	public Collection<Match> findByLevel(Level... levels) {
		return repository.findBy(levels);
	}

	private void before() {
		register(createMatch("1", Level.LEVEL1));
		register(createMatch("2", Level.LEVEL1));
		register(createMatch("3", Level.LEVEL3));
		register(createMatch("4", Level.LEVEL3));
		register(createMatch("5", Level.LEVEL3));
		register(createMatch("6", Level.LEVEL4));
	}

	private Match createMatch(String id, Level level) {
		LocalDate date = LocalDate.now();
		Match match = new Match();
		match.setIdentifier(new Identifier(id));
		match.setPlace("場所");
		match.setDate(date);
		match.setTime("2時間");
		match.setMaxPlayers(BigDecimal.TEN);
		match.setLevel(level);
		return match;
	}

	/**
	 * 全件検索します
	 *
	 * @return 募集一覧
	 */
	public List<Match> findAll() {
		return repository.findActivateDate();
	}
}
