package com.lyh.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CodeServlet1" ,urlPatterns = "/CodeServlet1")
public class CodeServlet1 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //使用hutool 工具创建验证码
        //1.创建验证码
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(300, 200);
        //2.拿到验证码
        String code = captcha.getCode();
        //3.获取session
        HttpSession session = req.getSession();
        //4.把验证码放入带session中
        session.setAttribute("code",code);
        //5.将验证码 发送到 浏览器
        captcha.verify("1234");
        ServletOutputStream outputStream = resp.getOutputStream();
        captcha.write(outputStream);
        outputStream.close();
    }
}
