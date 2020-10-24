package pl.michalzadrozny.asmodule2.service;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import pl.michalzadrozny.asmodule2.model.AppUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@Service
public class AppUserList {

    private final List<AppUser> userList;

    public AppUserList() {
        this.userList = new ArrayList<>();

        userList.add(new AppUser("Jan", "Jan123", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));
        userList.add(new AppUser("Admin", "Admin123", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))));
    }

    public Optional<AppUser> getUserByName(String name) {
        return userList
                .stream()
                .filter(user -> user.getUsername().equals(name))
                .findAny();
    }
}
