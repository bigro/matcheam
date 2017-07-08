package matcheam.match;

import matcheam.support.TestContext;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

/**
 * Created by ooguro on 2017/07/08.
 */
public class MatchRepositoryTest {

	private MatchRepository matchRepository;

	@Before
	public void setUp() throws Exception {
		TestContext testContext = new TestContext();
		matchRepository = new MatchRepository(testContext.dslContext());
	}

	@Test
	public void 全件検索できること() throws Exception {
		List<Match> actual = matchRepository.findAll();
		assertThat(actual).isNotEmpty();
	}
}


