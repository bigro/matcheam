package matcheam.match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import matcheam.entry.Entry;
import matcheam.entry.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import matcheam.entry.EntryUser;
import matcheam.matching.Matching;
import matcheam.matching.MatchingService;

/**
 * Created by ooguro on 2017/01/21.
 */
@Controller
@RequestMapping("/match")
public class MatchController {
	@Autowired
	MatchService matchService;

	@Autowired
	MatchingService matchingService;

	@Autowired
	EntryService entryService;

	@ModelAttribute("match")
	Match match() {
		return new Match();
	}

	@GetMapping("register")
	String show(Model model) {
		model.addAttribute("levels", Level.values());
		return "/match/register";
	}

	@PostMapping("result")
	String execute(@ModelAttribute("match") Match match, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return show(model);
		}
		matchService.register(match);
		return "/match/result";
	}

	/**
	 * 検索します。
	 * @param model テンプレートが表示するときに使う情報の設定先
	 * @param level レベル
	 * @return 表示するテンプレート
	 */
	@RequestMapping("/search")
	public String search(Model model, @RequestParam(required = false) Level level) {
		if (level == null) {
			Collection<Match> matches = matchService.findAll();
			model.addAttribute("levels", LevelCandidate.values());
			model.addAttribute("level", "");
			model.addAttribute("matches", matches);
			return "match/search";
		}
		try {
			Collection<Match> matches = matchService.findByLevel(level);
			model.addAttribute("levels", LevelCandidate.values());
			model.addAttribute("level", level);
			model.addAttribute("matches", matches);
		} catch (IllegalArgumentException e) {
			model.addAttribute("levels", LevelCandidate.values());
			model.addAttribute("level", level);
			model.addAttribute("matches", new ArrayList<Match>());
		}
		return "match/search";
	}

	@GetMapping("matching/{matchId}")
	public String matching(Model model, @ModelAttribute("entryName") String name, @PathVariable String matchId) throws Exception {
		Match match = matchService.findBy(new Identifier(matchId));
		EntryUser entryUser = new EntryUser(name);
		Entry entry = new Entry(match, Arrays.asList(entryUser));
		// TODO:本来はEntryクラスを渡したくはないかも
		entryService.entry(entry);
		return "redirect:/match/detail/" + matchId;
	}

	@GetMapping("detail/{matchId}")
	public String detail(Model model, @PathVariable String matchId) {
		Match match = matchService.findBy(new Identifier(matchId));
		model.addAttribute(match);
		Matching matching = matchingService.get(match);
		// TODO 永続化できたら null 考慮しないように修正する
		if (matching == null) {
			model.addAttribute("entryUserList", Collections.emptyList());
		} else {
			model.addAttribute("entryUserList", matching.getEntryUserList());
		}
		return "match/detail";
	}
}
