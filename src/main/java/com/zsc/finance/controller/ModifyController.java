package com.zsc.finance.controller;

import com.zsc.finance.common.Msg;
import com.zsc.finance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ModifyController {

    @Autowired
    UserService userService;
    //跳转到更新密码页面

    @PostMapping("/modify")
    public String modifyPassword(@RequestParam("username") String username, Model model) {
        model.addAttribute("username", username);
        return "resetPassword";
    }

    @PostMapping("/updatePassword/{username}/{password}")
    @ResponseBody
    public Msg updatePassword(@PathVariable("username") String username, @PathVariable("password") String newPassword) {
        System.out.println("进入updatePassword");
        userService.updateUserPassword(username, newPassword);
        return Msg.success().add("url", "/");
    }
}
