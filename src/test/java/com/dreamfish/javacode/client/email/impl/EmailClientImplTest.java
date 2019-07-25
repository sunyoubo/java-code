package com.dreamfish.javacode.client.email.impl;

import freemarker.template.TemplateException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: create by syb
 * @version: v1.0
 * @description: com.dreamfish.javacode.client.email.impl
 * @date:19-7-25 下午2:09
 */
@Ignore
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EmailClientImplTest {

    @Autowired
    private EmailClientImpl emailClient;

    @Test
    public void sendTextMail() {

        this.emailClient.sendTextMail("xxxxx@163.com", "测试邮件", "你怎么可以这么帅！！");
    }

    @Test
    public void sendHtmlMail() throws MessagingException {

        Map<String, String> attachmentMap = new HashMap<>();
        attachmentMap.put("附件1", "/tmp/a.txt");
        this.emailClient.sendHtmlMail("xxxxx@163.com", "测试邮件",
                "欢迎进入<a href=\\\"http://www.baidu.com\\\">百度首页</a>", attachmentMap);
    }

    @Test
    public void sendTemplateMail() throws MessagingException, IOException, TemplateException {

        Map<String, Object> params = new HashMap<>();
        params.put("username", "Cat");
        this.emailClient.sendTemplateMail("xxxxx@163.com", "测试邮件", params);

    }


}