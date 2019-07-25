package com.dreamfish.javacode.client.email.impl;

import com.dreamfish.javacode.client.email.EmailClient;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import freemarker.template.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author: create by syb
 * @version: v1.0
 * @description: com.dreamfish.javacode.client.email.impl
 * @date:19-7-25 上午11:51
 */
@Service
public class EmailClientImpl implements EmailClient {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    /**
     * 发送文本邮件.
     *
     * @param receiver : 邮件接收者
     * @param subject  : 邮件主题
     * @param text     : 邮件内容
     */
    @Override
    public void sendTextMail(String receiver, String subject, String text) {

        Objects.requireNonNull(receiver);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(this.mailProperties.getUsername());
        mailMessage.setTo(receiver);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        this.javaMailSender.send(mailMessage);
    }

    /**
     * 发送html邮件.
     *
     * @param receiver      : 邮件接收者
     * @param subject       : 邮件主题
     * @param text          : 邮件内容
     * @param attachmentMap : 附件map
     */
    @Override
    public void sendHtmlMail(String receiver, String subject, String text, Map<String, String> attachmentMap)
            throws MessagingException {

        Objects.requireNonNull(receiver);
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        //是否发送的邮件是富文本（附件，图片，html等）
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom(this.mailProperties.getUsername());
        messageHelper.setTo(receiver);
        messageHelper.setSubject(subject);
        messageHelper.setText(text, true);

        if(attachmentMap != null){
            attachmentMap.entrySet().stream().forEach(entrySet -> {
                try {
                    File file = new File(entrySet.getValue());
                    if(file.exists()){
                        messageHelper.addAttachment(entrySet.getKey(), new FileSystemResource(file));
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });
        }

        this.javaMailSender.send(mimeMessage);

    }

    /**
     * 发送模板邮件.
     *
     * @param receiver : 邮件接收者
     * @param subject  : 邮件主题
     * @param params   : 模板参数
     */
    @Override
    public void sendTemplateMail(String receiver, String subject, Map<String, Object> params)
            throws MessagingException, IOException, TemplateException {

        Objects.requireNonNull(receiver);
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(this.mailProperties.getUsername());
        helper.setTo(receiver);
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassForTemplateLoading(this.getClass(), "/templates");

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate("mail.ftl"), params);

        helper.setSubject(subject);
        helper.setText(html, true);

        this.javaMailSender.send(mimeMessage);

    }
}
