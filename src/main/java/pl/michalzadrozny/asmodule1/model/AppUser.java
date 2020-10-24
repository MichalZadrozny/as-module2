package pl.michalzadrozny.asmodule1.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class AppUser extends User {

    private int numberOfSuccessAuth;

    public AppUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.numberOfSuccessAuth = 0;
    }

    public void incrementNumberOfSuccessAuths() {
        numberOfSuccessAuth++;
    }
}
