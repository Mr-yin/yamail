package com.yzy.mail.controller;

import com.yzy.mail.Service.EmailService;
import com.yzy.mail.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;

@RestController
@RequestMapping("/email")
public class EmailController extends BaseController{
//    private static final String qq="1576306970@qq.com";

    @Resource
    EmailService emailService;

    @Resource
    TemplateEngine templateEngine ;

    @GetMapping("/1")
    public Tip sendSimpleMailTest(@ModelAttribute BaseEmailInfo baseEmailInfo){
        emailService.sendSimpleMail(baseEmailInfo.getTo(),baseEmailInfo.getSubject(),baseEmailInfo.getContent());
        return SUCCESS_TIP;
    }

    @GetMapping("/2")
    public Tip sendHtmlMail(@ModelAttribute BaseEmailInfo baseEmailInfo)  {
        emailService.sendHtmlMail(baseEmailInfo.getTo(),baseEmailInfo.getSubject(),baseEmailInfo.getContent());
        return SUCCESS_TIP;
    }

    @GetMapping("/3")
    public Tip sendAttachmentsMail(@ModelAttribute AttachmentsMailInfo attachmentsMailInfo) {
        String filePath = "D:\\test.xls";
        emailService.sendAttachmentsMail(attachmentsMailInfo.getTo(),attachmentsMailInfo.getSubject(),attachmentsMailInfo.getContent(),attachmentsMailInfo.getFilepath());
        return SUCCESS_TIP;
    }

    @GetMapping("/4")
    public Tip sendInlineMail(@ModelAttribute InineMailInfo inineMailInfo){
        String filePath = "D:\\1.jpg";
        String content = "<html><body>图片:<img src=\'cid:001\'/></body></html>";
        emailService.sendInlinResourceMail(inineMailInfo.getTo(),inineMailInfo.getSubject(),inineMailInfo.getContent(),inineMailInfo.getSrcpath(),inineMailInfo.getCid());
        return SUCCESS_TIP;
    }
    @GetMapping("/5")
    public Tip sendTemplateMail(@ModelAttribute BaseEmailInfo baseEmailInfo)throws Exception{
        Context context = new Context();
        context.setVariable("id","001");
        String emailContent = templateEngine.process("emailTemplate",context);
        emailService.sendHtmlMail(baseEmailInfo.getTo(),baseEmailInfo.getSubject(),emailContent);
        return SUCCESS_TIP;
    }
}
