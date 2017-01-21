package matcheam;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 募集検索コントローラ
 */
@Controller
@RequestMapping("/match")
public class MatchSearchController {
	private MatchService matchService = new MatchService();

	/**
	 * @param model
	 *            テンプレートが表示するときに使う情報の設定先
	 * @param level
	 *            レベル
	 * @return 表示するテンプレート
	 */
	@RequestMapping(value = "/search")
	public String search(Model model, @RequestParam(required = false) String level) {
		if (level == null || level.isEmpty()) {
			Collection<Match> matches = matchService.findAll();
			model.addAttribute("level", "");
			model.addAttribute("matches", matches);
			return "matchsearch";
		}
		try {
			Collection<Match> matches = matchService.findByLevel(Level.valueOf(level));
			model.addAttribute("level", level);
			model.addAttribute("matches", matches);
		} catch (IllegalArgumentException e) {
			model.addAttribute("level", level);
			model.addAttribute("matches", new ArrayList<Match>());
		}
		return "matchsearch";
	}
}