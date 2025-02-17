package cn.bdqn.util;

import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginlterLan implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String jie = (String) session.getAttribute("user");
        if(jie!=null){
            Jedis j=new Jedis();
            j.select(1);
            if (j.exists(jie)) {
                j.expire(jie,43200);
                j.close();
                return true;
            }else{
                j.close();
                response.setStatus(401);
                return false;
            }
        }else{
            response.setStatus(401);
            return false;
        }
    }
}
