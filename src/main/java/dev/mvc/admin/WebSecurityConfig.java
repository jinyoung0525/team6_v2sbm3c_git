package dev.mvc.admin;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public AuthenticationFailureHandler authenticationFailureHandler;

    // /: http://localhost:9091
    // /*: http://localhost:9091/모든 주소
    // /**: http://localhost:9091/하위 폴더를 포함한 모든 주소
    // .antMatchers("/member/**").hasAnyRole("USER", "ADMIN")  // DBMS: ROLE_USER, ROLE_ADMIN이 선언되어 있어야함.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*", "/test/**").permitAll()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/menu/**").permitAll()
                .antMatchers("/categrp/**").permitAll()
                .antMatchers("/cate/**").permitAll()
                .antMatchers("/contents/**").permitAll()
                .antMatchers("/member/**").permitAll()
                .antMatchers("/cart/**").permitAll()                
                .antMatchers("/admin/**").hasRole("ADMIN")  // DBMS: ROLE_ADMIN이 선언되어 있어야함.
                .anyRequest().authenticated();
 
        http.formLogin()
                .loginPage("/admin/login_form.do")  // SecurityCont.java 로그인 주소
                .loginProcessingUrl("/spring_security_check.do") // 자동 구현됨
                //.failureUrl("/login_form.do?error")    // default : /login?error
                .failureHandler(authenticationFailureHandler)
                //.defaultSuccessUrl("/")
                .usernameParameter("id")               // default : username
                .passwordParameter("password")     // default : password
                .permitAll();
 
        http.logout()
                .logoutUrl("/admin/logout.do")         // SecurityCont.java 로그아웃 주소
                .logoutSuccessUrl("/")            // logout 성공시 이동 주소
                .permitAll();
        
        // RESTful 또는 개발중에는 꺼 놓는다.
        // http.csrf().disable();

    }
 
    // 암호화 확인을위해 개발시에만 활성화
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//            .withUser("user1").password(passwordEncoder().encode("1234")).roles("USER")
//            .and()
//            .withUser("admin1").password(passwordEncoder().encode("1234")).roles("ADMIN");
//    }
  
    @Autowired
    private DataSource dataSource;
  
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("--> 1234 암호화: " + passwordEncoder().encode("1234"));

        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery(" SELECT id as username, password, enabled"
                                            + " FROM admin"
                                            + " WHERE id = ?")
            .authoritiesByUsernameQuery(" SELECT  id as username, authority"
                                                    + " FROM admin"
                                                    + " WHERE id = ?")
            .passwordEncoder(new BCryptPasswordEncoder());
    }
  
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}