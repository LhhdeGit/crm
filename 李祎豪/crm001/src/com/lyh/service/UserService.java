package com.lyh.service;

import com.lyh.bean.User;
import com.lyh.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    //登录
    public Map  login(String username, String password, HttpServletRequest request){
        Map map=new HashMap();
        //service 层要调用dao层
        UserDao dao = new UserDao();
        User userFromDB = dao.login(username, password);
        if(null==userFromDB){
            map.put("code",4001);
            map.put("msg","账户名或者密码不正确");
            return map;
        }else {
            HttpSession session=request.getSession();
            session.setAttribute("user",userFromDB);
            map.put("code",0);
            map.put("msg","登录成功");
            return map;
        }
    }
}
