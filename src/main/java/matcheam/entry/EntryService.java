package matcheam.entry;

import org.springframework.stereotype.Service;

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
	 */
	public void entry(Entry entry) {
		repository.register(entry);
	}
}
