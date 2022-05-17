package com.shop.controller.admin;

import com.shop.dto.UserAdminDto;
import com.shop.entity.User;
import com.shop.mapper.UserAdminMapper;
import com.shop.service.RoleService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserAdminMapper userAdminMapper;

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", new UserAdminDto());
        return "admin/addEditUser";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", userAdminMapper.toDto(userService.findById(id)));
        return "admin/addEditUser";
    }

    @GetMapping("/show")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/showUsers";
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute UserAdminDto user, BindingResult result, Model model) {
        User userExists = userService.findByEmail(user.getEmail());
        model.addAttribute("user", user);

        if (userExists != null && user.getId() == null) {
            model.addAttribute("error", "email");
            return "admin/addEditUser";
        }

        if (CollectionUtils.isEmpty(user.getRoles())) {
            model.addAttribute("error", "roles");
            return "admin/addEditUser";
        }

        if(result.hasErrors()) {
            return "admin/addEditUser";
        }

        if(user.getId() == null) {
            userService.saveNew(userAdminMapper.toEntity(user));
        } else {
            userService.update(userAdminMapper.toEntity(user));
        }

        return "redirect:/user/show?success";
    }
}
