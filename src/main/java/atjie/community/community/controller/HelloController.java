package atjie.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 创建时间 2020/5/19 14:57
 */
@Controller
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
