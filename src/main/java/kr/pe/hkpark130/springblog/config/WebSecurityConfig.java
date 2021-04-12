package kr.pe.hkpark130.springblog.config;

import kr.pe.hkpark130.springblog.config.handler.AuthenticationSuccessHandlerImpl;
import kr.pe.hkpark130.springblog.service.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    AuthenticationSuccessHandlerImpl authSuccessHandler;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests() // 접근에 대한 인증 설정이 가능
                .antMatchers("/login", "/").permitAll() // 누구나 접근 허용
//                .antMatchers("/admin", "/signup", "/user", "/posts/**").hasRole("ADMIN")
                .antMatchers("/admin", "/posts/**").hasRole("ADMIN") // ADMIN만 접근 가능
            .and()
                .formLogin() // 로그인 관한 설정
                .loginPage("/login") // 로그인 페이지 링크
                .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
                .successHandler(authSuccessHandler)
            .and()
                .logout() // 로그아웃 관한 설정
                .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true); //세션 날리기
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 로그인할 때 필요한 정보
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
