package matcheam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jnishida on 2017/02/19.
 */
@Controller
public class ApplicationController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
