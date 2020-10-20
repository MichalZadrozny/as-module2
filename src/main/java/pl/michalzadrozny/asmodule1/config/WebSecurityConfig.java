package pl.michalzadrozny.asmodule1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        User user = new User("Jan", getPasswordEncoder().encode("Jan123"), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        User admin = new User("Admin", getPasswordEncoder().encode("Admin123"), Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));

        auth.inMemoryAuthentication().withUser(user);
        auth.inMemoryAuthentication().withUser(admin);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/forUser").hasRole("USER")
                .antMatchers("/forAdmin").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutSuccessUrl("/logged-out");
    }
}
