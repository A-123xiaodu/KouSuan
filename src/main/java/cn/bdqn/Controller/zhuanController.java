package cn.bdqn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/zhuan")
public class zhuanController {
    @GetMapping("zhuan")//跳转到主界面
    public String zhuan(){
        return "zhu";
    }
    @GetMapping("qishi")//跳转到开始界面
    public String qishi(){
        return "zhuye";
    }
    @GetMapping("deng")
    public String deng(){
        return "deng";
    }
}