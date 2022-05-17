package com.shop.controller;

import com.shop.dto.UserRegisterDto;
import com.shop.entity.User;
import com.shop.enumeration.UserRole;
import com.shop.mapper.UserRegisterMapper;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRegisterMapper userRegisterMapper;

    @GetMapping(value = "/login")
    public String loginPage(Authentication authentication) {
        if(authentication != null) {
            return "redirect:/" +  redirectToHomePageRole();
        }

        return "login";
    }

    @GetMapping(value = "/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserRegisterDto());
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerPage(@Valid @ModelAttribute UserRegisterDto user, BindingResult result, Model model) {
        User userExists = userService.findByEmail(user.getEmail());
        model.addAttribute("user", user);

        if (userExists != null) {
            model.addAttribute("error", "email");
            return "register";
        }

        if(result.hasErrors()) {
            return "register";
        }

        userService.saveNew(userRegisterMapper.toEntity(user));

        return "redirect:/login";
    }

    private String redirectToHomePageRole(){
        List<String> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        if (authorities.contains(UserRole.ROLE_ADMIN.name())) {
            return "admin";
        } else if (authorities.contains(UserRole.ROLE_USER.name())) {
            return "home";
        }

        return "accessDenied";
    }
}
