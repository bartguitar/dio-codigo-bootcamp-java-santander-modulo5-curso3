package br.com.dio.dioprojetomodulo5curso3springsecurity.auth.infrastructure.http;

import br.com.dio.dioprojetomodulo5curso3springsecurity.auth.infrastructure.persistence.entity.User;
import br.com.dio.dioprojetomodulo5curso3springsecurity.auth.infrastructure.persistence.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

    @GetMapping
    public String hello(@AuthenticationPrincipal User user) {
        return "Hello World " + user.getId();
    }

    @GetMapping("/influencer")
    @PreAuthorize("hasRole('INFLUENCER')")
    public String influencerEndpoint(){
        return "Hello INFLUENCER";
    }

    @GetMapping("/brand")
    @PreAuthorize("hasRole('BRAND')")
    public String brandEndpoint(){
        return "Hello BRAND";
    }
}
