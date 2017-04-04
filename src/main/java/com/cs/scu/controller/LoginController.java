package com.cs.scu.controller;

import com.cs.scu.entity.User;
import com.cs.scu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by maicius on 2017/3/31.
 */
@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/userLogin")
    public ModelAndView Test(
                             @RequestParam(value = "userName") String userName,
                             @RequestParam(value = "password") String password) throws Exception {
        User user = new User(userName, password);
        System.out.println(userName);
        ModelAndView mv = new ModelAndView();
        User loginUser = loginService.doUserLogin(user);
        System.out.println("login_result:" + loginUser.toString());
        mv.setViewName("login");
        System.out.println("Controller finished");
        return mv;
    }

}
