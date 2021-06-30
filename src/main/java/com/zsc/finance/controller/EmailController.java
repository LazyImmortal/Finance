package com.zsc.finance.controller;

import com.zsc.finance.common.Msg;
import com.zsc.finance.service.impl.SendEmailServiceImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Controller
public class EmailController {
    @Autowired
    SendEmailServiceImpl emailService;

    @Autowired
    TemplateEngine templateEngine;

    @GetMapping("/sendCaptcha/{email}/{username}")
    @ResponseBody
    public Msg sendCaptcha(@PathVariable("email") String receiver, @PathVariable("username") String username) {
        //780641438@qq.com
        Msg res = new Msg();
        try {
            String subject = "忘记密码功能测试";
            Context context = new Context();
            context.setVariable("username", username);
            String code = RandomString.make(5);
            System.out.println("code: " + code); //控制台打印验证码
            context.setVariable("code", code);
            String emailContent = templateEngine.process("emailTemplate", context);
            //发送邮件，调试时可以注释掉
            emailService.sendTemplateEmail(receiver, subject, emailContent);

            //emailService.sendEmail(receiver, "验证码", context.toString());
            res.setMsg(code); //验证码
            res.setCode(100);
        }catch (Exception e) {
            res.setCode(200);
            res.setMsg(""); //验证码
            res.setMsg("发送失败");
        }finally {
            return res;
        }
    }
}
