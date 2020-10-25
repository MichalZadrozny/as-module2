package pl.michalzadrozny.asmodule2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.michalzadrozny.asmodule2.model.AppUser;
import pl.michalzadrozny.asmodule2.service.AppUserList;

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

        String hello = "Cześć admin " + principal.getName();

        return userList.getUserByName(principal.getName())
                .map(user -> hello + "! Liczba logowań w serwisie: " + user.getNumberOfSuccessAuth())
                .orElse(hello);
    }

    @GetMapping("/forUser")
    public String forUser(Principal principal) {

        String hello = "Cześć user " + principal.getName();

        return userList.getUserByName(principal.getName())
                .map(user -> hello + "! Liczba logowań w serwisie: " + user.getNumberOfSuccessAuth())
                .orElse(hello);
    }

    @GetMapping("/logged-out")
    public String loggedOut() {
        return "Papa ";
    }
}
