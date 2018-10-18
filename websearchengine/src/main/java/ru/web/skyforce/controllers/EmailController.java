package ru.web.skyforce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.MimeMessage;

/**
 * Date 19.10.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
@Controller
public class EmailController {

    @Autowired
    private JavaMailSender sender;

    @RequestMapping(value = "/sendMail", method = RequestMethod.GET)
    @ResponseBody
    public void sendMail(@RequestParam String topic, String message) {
        try {
            sendEmail(topic, message);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendEmail(String topic, String msg) throws Exception{
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setTo("demirel6777@gmail.com");
        helper.setText(msg);
        helper.setSubject(topic);

        sender.send(mimeMessage);
    }
}
