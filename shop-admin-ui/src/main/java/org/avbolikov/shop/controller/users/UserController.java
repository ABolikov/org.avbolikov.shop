package org.avbolikov.shop.controller.users;

import org.avbolikov.shop.exception.NotFoundException;
import org.avbolikov.shop.representation.users.UserRepr;
import org.avbolikov.shop.service.RoleServiceImpl;
import org.avbolikov.shop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserServiceImpl userService;

    private final RoleServiceImpl roleService;

    @Autowired
    public UserController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/admin/users/add")
    public String addUser(Model model) {
        model.addAttribute("user",  new UserRepr());
        model.addAttribute("roles", roleService.findAll());
        return "user";
    }

    @GetMapping("/admin/user/{id}/edit")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", userService.findById(id).orElseThrow(new NotFoundException(null, "User")));
        model.addAttribute("roles", roleService.findAll());
        return "user";
    }

    @PostMapping("/admin/user/update")
    public String updateUser(@Valid UserRepr user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.findAll());
            return "user";
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/user/{id}/delete")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

}
