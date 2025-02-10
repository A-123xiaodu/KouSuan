package cn.bdqn.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zhuan")
public class zhuanController {
    @GetMapping("zhuan")
    public String zhuan(){
        return "zhuye";
    }
    @GetMapping("qishi")
    public String qishi(){
        return "zhu";
    }
}
