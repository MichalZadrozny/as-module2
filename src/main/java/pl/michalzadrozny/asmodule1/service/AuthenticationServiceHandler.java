package pl.michalzadrozny.asmodule1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import pl.michalzadrozny.asmodule1.model.AppUser;

import java.util.Optional;

@Service
public class AuthenticationServiceHandler {

    private final AppUserList userList;

    @Autowired
    public AuthenticationServiceHandler(AppUserList userList) {
        this.userList = userList;
    }

    @EventListener
    public void onApplicationSuccessEvent(AuthenticationSuccessEvent event) {

        Optional<AppUser> appUser = userList.getUserByName(((User) event.getAuthentication().getPrincipal()).getUsername());

        if (appUser.isPresent()) {
            appUser.get().incrementNumberOfSuccessAuths();
        }
    }
}
