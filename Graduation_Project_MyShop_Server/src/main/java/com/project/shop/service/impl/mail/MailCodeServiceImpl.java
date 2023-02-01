package com.project.shop.service.impl.mail;

import com.project.shop.pojo.bo.EmailCodeBo;
import com.project.shop.pojo.role.Admin;
import com.project.shop.service.inter.mail.MailCodeService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class MailCodeServiceImpl implements MailCodeService {
    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    private static final long time=1000*60;
    public static final String emailSignature= "email";

    @Override
    public String createEmailCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i=0; i<6; i++){
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    @Override
    public String createEmailValidateToken(EmailCodeBo emailCodeBo) {
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken=jwtBuilder
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .claim("email",emailCodeBo.getEmail())
                .claim("code",emailCodeBo.getCode())
                .setSubject("email-register")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256,emailSignature)
                .compact();
        return jwtToken;
    }

    @Override
    public EmailCodeBo checkEmailCode(String token) {
        if (token==null){
            return null;
        }
        JwtParser jwtParser = Jwts.parser();
        EmailCodeBo emailCodeBo = null;
        try {
            Jws<Claims> claimsJws = jwtParser.setSigningKey(emailSignature).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            emailCodeBo = new EmailCodeBo();
            emailCodeBo.setEmail((String) claims.get("email"));
            emailCodeBo.setCode((String) claims.get("code"));
        } catch (Exception e){
            return null;
        }
        return emailCodeBo;
    }

    @Override
    public Boolean sendEmailCode(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage(this.templateMessage);
        message.setTo(toEmail);
        String warring = "这是一个正在测试中的实验系统,可能会造成邮箱误发,对此深表歉意";
        String enWarring = "This is an experimental system under test, which may cause the email to be sent by mistake. We apologize for this";
        String mailMessage = warring + "\n" + enWarring + "\n" +"\n"+ "正在进行账号注册,绑定当前邮箱,验证码为:"+code;
        message.setText(mailMessage);
        try {
            this.mailSender.send(message);
            return true;
        }catch (MailException e){
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Autowired
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    @Autowired
    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
}
