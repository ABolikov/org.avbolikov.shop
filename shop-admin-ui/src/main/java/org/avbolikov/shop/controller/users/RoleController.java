package org.avbolikov.shop.controller.users;

import org.avbolikov.shop.exception.NotFoundException;
import org.avbolikov.shop.representation.users.RoleRepr;
import org.avbolikov.shop.service.users.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class RoleController {

    private final RoleServiceImpl roleService;

    @Autowired
    public RoleController(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin/roles")
    public String getAllRole(Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "roles";
    }

    @GetMapping("/admin/roles/add")
    public String formAddRole(Model model) {
        model.addAttribute("role", new RoleRepr());
        return "role";
    }

    @GetMapping("/admin/role/{id}/edit")
    public String editRole(@PathVariable("id") Integer id, Model model) {
        RoleRepr role = roleService.findById(id).orElseThrow(new NotFoundException(null, "Role"));
        model.addAttribute("role", role);
        return "role";
    }

    @PostMapping("/admin/role/update")
    public String updateRole(@Valid RoleRepr role, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "role";
        }
        roleService.save(role);
        return "redirect:/admin/roles";
    }

    @DeleteMapping("/admin/role/{id}/delete")
    public String deleteRole(@PathVariable("id") Integer id) {
        roleService.delete(id);
        return "redirect:/admin/roles";
    }
}
