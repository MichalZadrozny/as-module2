package pl.michalzadrozny.asmodule1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.michalzadrozny.asmodule1.model.AppUser;
import pl.michalzadrozny.asmodule1.service.AppUserList;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AppUserList userList;

    @Autowired
    public WebSecurityConfig(AppUserList userList) {
        this.userList = userList;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        authenticateUser(auth, userList.getUserList().get(0));
        authenticateUser(auth, userList.getUserList().get(1));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/forUser").hasAnyRole("USER", "ADMIN")
                .antMatchers("/forAdmin").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutSuccessUrl("/logged-out");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private void authenticateUser(AuthenticationManagerBuilder auth, AppUser user) throws Exception {
        auth.inMemoryAuthentication().withUser(new AppUser(user.getUsername(), getPasswordEncoder().encode(user.getPassword()), user.getAuthorities()));
    }


}
