package pl.michalzadrozny.asmodule1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestApi {

    @GetMapping("/forAll")
    public String forAll(){
        return "Cześć nieznajomy";
    }

    @GetMapping("/forAdmin")
    public String forAdmin(Principal principal){
        return "Cześć admin " + principal.getName();
    }

    @GetMapping("/forUser")
    public String forUser(Principal principal){
        return "Cześć user " + principal.getName();

    }    @GetMapping("/logged-out")
    public String loggedOut(){
        return "Papa ";
    }
}
