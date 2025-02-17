package cn.bdqn.controller;

import cn.bdqn.service.JiSuanService;
import cn.bdqn.util.tiUtil;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;
import java.io.FileWriter;
import java.io.IOException;

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
    @GetMapping("xuan")//通过前端选择的符号进行计算，并答完当前题之后的正确率
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
    @GetMapping("/countP")//判断前端输入的题数是否为数字
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
    @GetMapping("/print")
    public String print(String zhengL,String tS,String zhengG) throws IOException {
        FileWriter fileWriter=new FileWriter("KouSuan\\zhanJi.txt");
        fileWriter.write("最新战绩\r\n正确率："+zhengL+"\r\n"+"题数："+tS+"\r\n"+zhengG+"\r\n");
        fileWriter.close();
        return "true";
    }
    @GetMapping("getName")
    public String yan(String name){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        if (jiSuanService.getName(name)!=null) {
            String yan = tiUtil.getYan();
            jedis.select(2);
            jedis.set(name,yan);
            jedis.expire(name,300);
            jedis.close();
            return yan;
        }else{
            jedis.close();
            return "false";
        }
    }
    @GetMapping("Deng")
    public String Deng(String username, String yan, HttpSession session){
        yan=yan==null?"":yan;
        Jedis jedis=new Jedis("127.0.0.1",6379);
        if (jiSuanService.getName(username)!=null && yan.matches("\\d{6}")) {
            jedis.select(2);
            if (jedis.exists(username) && jedis.get(username).equals(yan)) {
                jedis.select(1);
                jedis.set(username,"1");
                jedis.expire(username,43200);
                session.setAttribute("user",username);
                return "true";
            }else{
                jedis.close();
                return "false";
            }
        }else{
            jedis.close();
            return "mG";
        }
    }
}