package com.lyh.controller;

import com.alibaba.fastjson.JSONObject;
import com.lyh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "LoginServlet" ,urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1.修正 编码格式
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html; charset=UTF-8");

        //2.接收前端过来的3个参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String code = req.getParameter("code");

        //3.登录的时候，首先验证 验证码是否正确
        // 3.1获取 后台的验证码
        HttpSession session = req.getSession();
        String codeFromSession = (String) session.getAttribute("code");
        System.out.println("codeFromSession = " + codeFromSession);
        if (!codeFromSession.equals(code)){
            //验证错误 注意前面有！
            //向前端 输入一段json告知前端 验证码错误
            PrintWriter writer = resp.getWriter();
            Map map = new HashMap();
            map.put("code",400);
            map.put("msg","验证码不正确");
            //把map变成json
            String jsonString = JSONObject.toJSONString(map);
            writer.print(jsonString);
            writer.close();
         }else{
            //验证码正确 继续 判断 账号和密码
            System.out.println("验证码正确，该判断账号和密码了");
            //就需要service/dao层判断，如果咱们业务不是特别多，按摩可以直接不用service层
            UserService service = new UserService();
            Map map = service.login(username,password,req);
            String jsonString = JSONObject.toJSONString(map);
            PrintWriter writer = resp.getWriter();
            writer.print(jsonString);
            writer.close();
        }
    }
}