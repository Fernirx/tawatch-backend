package vn.fernirx.tawatch.infrastructure.message;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import vn.fernirx.tawatch.infrastructure.properties.CacheProperties;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final CacheProperties cacheProperties;

    @Async
    public void sendMail(String to, String subject, String htmlContent) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            mailSender.send(mimeMessage);
            log.info("Email sent successfully to: {}", to);
        } catch (MessagingException e) {
            log.error("Failed to send email to: {}", to, e);
        }
    }

    @Async
    public void sendForgotPasswordOtp(String to, String userName, String otpCode) {
        String subject = "TAwatch - Mã Xác Thực Đặt Lại Mật Khẩu";
        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("otpCode", otpCode);
        context.setVariable("expiryMinutes", cacheProperties.getOtp().getExpireAfterWrite().toMinutes());
        String htmlContent = templateEngine.process("mail/forgot-password-otp", context);
        sendMail(to, subject, htmlContent);
    }

    @Async
    public void sendVerifyEmailOtp(String to, String userName, String otpCode) {
        String subject = "TAwatch - Mã Xác Thực Email";
        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("otpCode", otpCode);
        context.setVariable("expiryMinutes", cacheProperties.getOtp().getExpireAfterWrite().toMinutes());
        String htmlContent = templateEngine.process("mail/verify-email-otp", context);
        sendMail(to, subject, htmlContent);
    }
}