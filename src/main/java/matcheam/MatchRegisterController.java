package matcheam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ooguro on 2017/01/21.
 */
@Controller
@RequestMapping("/match")
public class MatchRegisterController {
    @Autowired
    MatchService matchService;

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
}
