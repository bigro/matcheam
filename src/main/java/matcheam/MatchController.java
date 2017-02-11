package matcheam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

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
    EntryUserService entryUserService;

    @ModelAttribute("match")
    Match match() {
        return new Match();
    }

    @GetMapping("register")
    String show() {
        return "/match/register";
    }

    @PostMapping("result")
    String execute(@ModelAttribute("match") Match match, BindingResult bindingResult) {
        matchService.register(match);
        return "/match/result";
    }

    /**
     * @param model テンプレートが表示するときに使う情報の設定先
     * @param level レベル
     * @return 表示するテンプレート
     */
    @RequestMapping(value = "/search")
    public String search(Model model, @RequestParam(required = false) String level) {
        if (level == null || level.isEmpty()) {
            Collection<Match> matches = matchService.findAll();
            model.addAttribute("level", "");
            model.addAttribute("matches", matches);
            return "match/search";
        }
        try {
            Collection<Match> matches = matchService.findByLevel(Level.valueOf(level));
            model.addAttribute("level", level);
            model.addAttribute("matches", matches);
        } catch (IllegalArgumentException e) {
            model.addAttribute("level", level);
            model.addAttribute("matches", new ArrayList<Match>());
        }
        return "match/search";
    }

    @PostMapping(value = "{id}/matching")
    public String matching(@ModelAttribute("userName") String name, @PathVariable String id) {
        Match match = matchService.findOne(id);
        EntryUser entryUser = new EntryUser(name);
        matchingService.apply(match, entryUser);
        return "/match/detail/" + name;
    }
}
