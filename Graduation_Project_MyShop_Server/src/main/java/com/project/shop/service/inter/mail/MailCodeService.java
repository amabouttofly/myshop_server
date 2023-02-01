package com.project.shop.service.inter.mail;

import com.project.shop.pojo.bo.EmailCodeBo;

public interface MailCodeService {

    public String createEmailCode();
    public String createEmailValidateToken(EmailCodeBo emailCodeBo);
    public EmailCodeBo checkEmailCode(String token);
    public Boolean sendEmailCode(String toEmail, String code);

}
