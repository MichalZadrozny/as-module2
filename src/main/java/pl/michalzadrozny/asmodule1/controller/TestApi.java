package pl.michalzadrozny.asmodule1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.michalzadrozny.asmodule1.model.AppUser;
import pl.michalzadrozny.asmodule1.service.AppUserList;

import java.security.Principal;
import java.util.Optional;

@RestController
public class TestApi {

    private final AppUserList userList;

    @Autowired
    public TestApi(AppUserList appUserList) {
        this.userList = appUserList;
    }

    @GetMapping("/forAll")
    public String forAll() {
        return "Cześć nieznajomy";
    }

    @GetMapping("/forAdmin")
    public String forAdmin(Principal principal) {
        Optional<AppUser> appUser = userList.getUserByName(principal.getName());

        if (appUser.isPresent()) {
            return "Cześć admin " + principal.getName() + "! Liczba logowań w serwisie: " + appUser.get().getNumberOfSuccessAuth();
        }

        return "Cześć admin " + principal.getName();
    }

    @GetMapping("/forUser")
    public String forUser(Principal principal) {
        Optional<AppUser> appUser = userList.getUserByName(principal.getName());

        if (appUser.isPresent()) {
            return "Cześć user " + principal.getName() + "! Liczba logowań w serwisie: " + appUser.get().getNumberOfSuccessAuth();
        }

        return "Cześć user " + principal.getName();
    }

    @GetMapping("/logged-out")
    public String loggedOut() {
        return "Papa ";
    }
}
