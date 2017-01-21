package matcheam;

import org.springframework.stereotype.Controller;
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
    @ModelAttribute("match")
    Match match() {
        return new Match();
    }

    @GetMapping("register")
    String show() {
        return "/match/register";
    }

    @PostMapping("result")
    String register(@ModelAttribute("match") Match match) {
        return "/match/result";
    }
}
