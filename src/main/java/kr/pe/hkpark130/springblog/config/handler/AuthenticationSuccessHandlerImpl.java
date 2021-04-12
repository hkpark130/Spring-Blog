package kr.pe.hkpark130.springblog.config.handler;

import kr.pe.hkpark130.springblog.domain.users.UserInfo;
import kr.pe.hkpark130.springblog.domain.users.UserRepository;
import kr.pe.hkpark130.springblog.web.dto.UserInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@Slf4j
@Component
public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    HttpSession session;

    @Autowired
    UserRepository repository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
        String userName = "";

        if(authentication.getPrincipal() instanceof Principal) {
            userName = ((Principal)authentication.getPrincipal()).getName();
        } else {
            userName = ((UserInfo)authentication.getPrincipal()).getUsername();
        }

        session.setAttribute("username", userName);
        super.onAuthenticationSuccess(request, response, authentication);

    }


}
