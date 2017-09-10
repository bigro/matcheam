package matcheam.entry;

import matcheam.match.Match;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 応募サービスです。
 *
 * @since 1.0
 */
@Service
public class EntryService {

	private EntryRepository repository;

	public EntryService(EntryRepository repository) {
		this.repository = repository;
	}

	/**
	 * 応募します。
	 * @param entry 応募
	 * @throws Exception 登録に失敗した時
	 */
	public void entry(Entry entry) throws Exception {
		repository.register(entry);
	}

	public List<EntryUser> findEntryUserBy(Match match) {
		return repository.findEntryUserBy(match);
	}
}
