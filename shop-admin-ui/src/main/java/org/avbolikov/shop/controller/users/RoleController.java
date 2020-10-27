package org.avbolikov.shop.controller.users;

import org.avbolikov.shop.entity.users.Role;
import org.avbolikov.shop.exception.NotFoundException;
import org.avbolikov.shop.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/admin/roles")
    public String allRole(Model model) {
        model.addAttribute("rolePage", roleRepository.findAll());
        return "roles";
    }

    @GetMapping("/admin/roles/add")
    public String formAddRole(Model model) {
        model.addAttribute("role", new Role());
        return "role";
    }


    @GetMapping("/admin/role/{id}/edit")
    public String editRole(@PathVariable("id") Integer id, Model model) {
        Role role = roleRepository.findById(id).orElseThrow(new NotFoundException(null, "Role"));
        model.addAttribute("role", role);
        return "role";
    }

    @PostMapping("/admin/role/update")
    public String updateRole(@Valid Role role, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "role";
        }
        roleRepository.save(role);
        return "redirect:/admin/roles";
    }

    @DeleteMapping("/admin/role/{id}/delete")
    public String deleteRole(@PathVariable("id") Integer id) {
        roleRepository.deleteById(id);
        return "redirect:/admin/roles";
    }
}
