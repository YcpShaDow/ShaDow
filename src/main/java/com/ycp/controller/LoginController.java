package com.ycp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by ycp on 2018/9/4.
 */
@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login(HttpSession session,String username,String password){
        session.setAttribute("username",username);
        return "redirect:/items/queryItems.action";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/items/queryItems.action";
    }
}
