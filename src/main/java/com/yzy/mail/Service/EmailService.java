package com.yzy.mail.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {

    private static final Logger logger= LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private JavaMailSender mailSender ;

    @Value("${spring.mail.username}")
    private String from ;


    /**
     * 发送简单邮件
     * @param to 给谁发送
     * @param subject 主题
     * @param content 内容
     */
    public void sendSimpleMail(String to , String subject, String content){
        logger.info("发送网页邮件开始：{},{},{}",to,subject,content);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);
        mailSender.send(message);
        logger.info("发送网页邮件成功：{},{},{}",to,subject,content);
    }

    /**
     * 发送网页邮箱
     * @param to 给谁发送
     * @param subject 主题
     * @param content 内容
     */
    public void sendHtmlMail(String to , String subject, String content){
        logger.info("发送网页邮件开始：{},{},{}",to,subject,content);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            message.setFrom(from);
            mailSender.send(message);
            logger.info("发送网页邮件成功：{},{},{}",to,subject,content);
        } catch (MessagingException e) {
            logger.info("发送网页邮件失败：{},{},{}",to,subject,content);
            logger.info("失败原因：{}",e.getMessage());
        }

    }

    /**
     * 发送附件邮件
     * @param to 给谁发送
     * @param subject 主题
     * @param content 内容
     * @param filePath 附件文件路径
     */
    public void sendAttachmentsMail(String to , String subject , String content,
                                    String filePath)  {
        logger.info("发送附件邮件开始：{},{},{},{}",to,subject,content,filePath);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            helper.addAttachment(fileName,file);
            mailSender.send(message);
            logger.info("发送附件邮件成功：{},{},{},{}",to,subject,content,filePath);
        } catch (MessagingException e) {
            logger.info("发送附件邮件失败：{},{},{},{}",to,subject,content,filePath);
            logger.info("失败原因：{}",e.getMessage());
        }
    }

    /**
     * 带图片邮件
     * @param to 给谁发送
     * @param subject 主题
     * @param content 内容
     * @param srcPath 图片路径
     * @param srcId 页面标识
     */
    public void sendInlinResourceMail(String to ,String subject , String content,
                                      String srcPath,String srcId) {
        logger.info("发送带图片邮件开始：{},{},{},{},{}",to,subject,content,srcPath,srcId);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            FileSystemResource resource = new FileSystemResource(new File(srcPath));
            helper.addInline(srcId,resource);
            mailSender.send(message);
            logger.info("发送带图片邮件成功：{},{},{},{},{}",to,subject,content,srcPath,srcId);
        } catch (MessagingException e) {
            logger.info("发送带图片邮件失败：{},{},{},{},{}",to,subject,content,srcPath,srcId);
            logger.info("失败原因：{}",e.getMessage());
        }
    }
}
