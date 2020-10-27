package org.avbolikov.shop.controller.users;

import org.avbolikov.shop.entity.users.Role;
import org.avbolikov.shop.entity.users.User;
import org.avbolikov.shop.exception.NotFoundException;
import org.avbolikov.shop.repositories.RoleRepository;
import org.avbolikov.shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/admin/users")
    public String allUsers(Model model) {
        model.addAttribute("usersPage", userRepository.findAll());
        return "users";
    }

    @GetMapping("/admin/users/add")
    public String addUser(Model model) {
        model.addAttribute("user",  new User());
        model.addAttribute("allRoles", roleRepository.findAll());
        return "user";
    }

    @GetMapping("/admin/user/{id}/edit")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userRepository.findById(id).orElseThrow(new NotFoundException(null, "User"));
        user.setPassword("");
        model.addAttribute("user", user);
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("allRoles", roles);
        return "user";
    }

    @PostMapping("/admin/user/update")
    public String updateUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allRoles", roleRepository.findAll());
            return "user";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/user/{id}/delete")
    public String deleteUser(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
        return "redirect:/admin/users";
    }
}
