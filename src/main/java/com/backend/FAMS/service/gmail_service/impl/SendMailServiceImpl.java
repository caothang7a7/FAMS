package com.backend.FAMS.service.gmail_service.impl;/*  Welcome to Jio word
    @author: Jio
    Date: 10/27/2023
    Time: 2:56 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/

import com.backend.FAMS.entity.user.User;
import com.backend.FAMS.service.gmail_service.ISendMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements ISendMailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendMailCreatedUser(User user, String password){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String emailContent = "Chúc mừng! Tài khoản của bạn đã được tạo thành công trong ứng dụng For Fresher Academy Management System (FAMS).\n" +
                    "\n" +
                    "Dưới đây là thông tin tài khoản của bạn:\n" +
                    "\n" +
                    "Tên dự án: For Fresher Academy Management System\n" +
                    "\n" +
                    "Tên người dùng: "+ user.getName()+"\n" +
                    "\n" +
                    "Email: "+ user.getEmail()+"\n" +
                    "\n" +
                    "Mật khẩu: "+ password+"\n" +
                    "\n" +
                    "Vai Trò: \n" +
                    "\n" +
                    "Vui lòng lưu trữ thông tin này một cách an toàn. Để bảo đảm tính bảo mật, bạn nên thay đổi mật khẩu của mình sau khi đăng nhập lần đầu tiên. Dưới đây là cách để thay đổi mật khẩu:\n" +
                    "\n" +
                    "1. Đăng nhập vào tài khoản của bạn tại Trang chủ.\n" +
                    "\n" +
                    "2. Truy cập phần \"Thay đổi mật khẩu\" trong tài khoản của bạn.\n" +
                    "\n" +
                    "3. Nhập mật khẩu hiện tại và sau đó nhập mật khẩu mới của bạn.\n" +
                    "\n" +
                    "4. Lưu thay đổi.\n" +
                    "\n" +
                        "Nếu bạn gặp bất kỳ khó khăn nào trong việc thay đổi mật khẩu hoặc có bất kỳ câu hỏi nào về tài khoản của bạn, xin vui lòng liên hệ với chúng tôi.\n" +
                    "\n" ;

            emailContent += "Chúng tôi rất vui được chào đón bạn* vào FAMS và hy vọng bạn sẽ có trải nghiệm tốt khi sử dụng dịch vụ của chúng tôi.\n" +
                    "\n"
                    + new Date() + "\n"
                    + "Thân ái\n"
                    + "FAMS.";

            // gui meo
            helper.setTo(user.getEmail());
            helper.setSubject("FAMS - user_controller Account was created");
            helper.setText(emailContent);

            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
