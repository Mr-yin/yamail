package com.yzy.mail;

import com.yzy.mail.Service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailApplicationTests {

	private static final String qq="1576306970@qq.com";

	@Resource
	EmailService emailService;

	@Resource
	TemplateEngine templateEngine ;
	@Test
	public void contextLoads() {
	}

	@Test
	public void sendSimpleMailTest(){
		emailService.sendSimpleMail(qq,"这是第一封邮件","这是邮件内容");
	}

	@Test
	public void sendHtmlMail() throws Exception{

		emailService.sendHtmlMail(qq,"这是第一封邮件","<h1>这是邮件内容</h1>");
	}

	@Test
	public void sendAttachmentsMail() throws Exception{

		String filePath = "D:\\test.xls";
		emailService.sendAttachmentsMail(qq,"这是带附件的邮件","<h1>这是带附件的邮件</h1>",filePath);
	}

	@Test
	public void sendInlineMail() throws Exception{
		String filePath = "D:\\1.jpg";
		String content = "<html><body>图片:<img src=\'cid:001\'/></body></html>";
		emailService.sendInlinResourceMail(qq,"这是带图片的邮件",content,filePath,"001");
	}

	@Test
	public void sendTemplateMail()throws Exception{
		Context context = new Context();
		context.setVariable("id","001");
		String emailContent = templateEngine.process("emailTemplate",context);
		emailService.sendHtmlMail(qq,"这是第一封模板邮件",emailContent);
	}

}

