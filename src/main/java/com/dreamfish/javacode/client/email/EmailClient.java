package com.dreamfish.javacode.client.email;

import freemarker.template.TemplateException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

/**
 * @author: create by syb
 * @version: v1.0
 * @description: com.dreamfish.javacode.client.email
 * @date:19-7-25 上午11:25
 */
public interface EmailClient {

    /**
     * 发送文本邮件.
     *
     * @param receiver : 邮件接收者
     * @param subject: 邮件主题
     * @param text: 邮件内容
     */
    void sendTextMail(String receiver, String subject, String text);

    /**
     * 发送html邮件.
     *
     * @param receiver : 邮件接收者
     * @param subject: 邮件主题
     * @param text: 邮件内容
     * @param attachmentMap: 附件map
     */
    void sendHtmlMail(String receiver, String subject, String text, Map<String, String> attachmentMap)
            throws MessagingException;

    /**
     * 发送模板邮件.
     *
     * @param receiver : 邮件接收者
     * @param subject: 邮件主题
     * @param params: 模板参数
     */
    void sendTemplateMail(String receiver, String subject, Map<String, Object> params)
            throws MessagingException, IOException, TemplateException;

}
