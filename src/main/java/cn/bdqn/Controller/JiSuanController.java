package cn.bdqn.Controller;

import cn.bdqn.service.JiSuanService;
import cn.bdqn.Util.tiUtil;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/jisuan")
public class JiSuanController {
    @Autowired
    @Qualifier("JiSuanService")
    private JiSuanService jiSuanService;

    @GetMapping("ti")
    public String ti(){
        return tiUtil.shizi();
    }
    @GetMapping("xuan")
    public String suan(String fu,String first,String second,String shu,String zheng,String tishu){
        String fuhao = fu.substring(0,1);
        int yi = Integer.parseInt(first);
        int er = Integer.parseInt(second);
        boolean jie=false;
        switch(fuhao){
            case ">":
                jie=yi>er;
                break;
            case "<":
                jie=yi<er;
                break;
            case "=":
                jie=yi==er;
                break;
        }
        int i = Integer.parseInt(zheng);
        int geshu = Integer.parseInt(shu);
        int zuo=geshu;
        if (jie) {
            i++;
        }
        String guo=(int)((double) i / geshu * 100)+"%-";
        geshu++;
        if(zuo==Integer.parseInt(tishu)){
            return guo+geshu+"-"+i+"-good";
        }else{
            return guo+geshu+"-"+i;
        }
    }
    @GetMapping("/countP")
    public String countP(String count){
        if(count==null || count.equals("") || count.equals("0")){
            return "false";
        }
        int length = count.length();
        String shi="\\d{"+length+"}";
        if(count.matches(shi)){
            return "true";
        }else{
            return "false";
        }
    }
}
