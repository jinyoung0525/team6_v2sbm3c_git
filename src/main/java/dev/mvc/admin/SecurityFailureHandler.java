package dev.mvc.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class SecurityFailureHandler implements AuthenticationFailureHandler {

    // 로그인 실패
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
    throws IOException, ServletException {
        System.out.println("-> SecurityFailureHandler onAuthenticationFailure()");
        String loginid = request.getParameter("id");
        String errormsg = "";
        
        if (exception instanceof BadCredentialsException) {
            loginFailureCount(loginid);
            errormsg = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            loginFailureCount(loginid);
            errormsg = "현재는 로그인 할 수 없습니다. 잠시후 다시 로그인해주세요.";
        } else if (exception instanceof DisabledException) {
            errormsg = "계정이 비활성화되었습니다. 관리자에게 문의하세요.";
        } else if (exception instanceof CredentialsExpiredException) {
            errormsg = "비밀번호 유효기간이 만료 되었습니다. 관리자에게 문의하세요.";
        }
    
        request.setAttribute("username",  loginid);
        request.setAttribute("error_message",  errormsg);
        
        request.getRequestDispatcher("/login_form.do?error").forward(request,  response);
    }

    // 비밀번호를 3번 이상 틀릴 시 계정 잠금 처리
    protected void loginFailureCount(String id) {
      System.out.println("6회이상 로그인 실패시 계정을 30분간 사용 못하게 됩니다.");
        /*
        // 틀린 횟수 업데이트
        userDao.countFailure(id);
        // 틀린 횟수 조회
        int cnt = userDao.checkFailureCount(id);
        if(cnt==3) {
            // 계정 잠금 처리
            userDao.disabledUsername(id);
        }
        */
    }
    
}