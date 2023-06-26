package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.servise.RoleServise;
import ru.kata.spring.boot_security.demo.servise.UserServise;


@Controller
@RequestMapping("/admin")
public class AdminControllers {

    private final RoleServise roleServise;
    private final UserServise userServise;

    public AdminControllers(RoleServise roleServise, UserServise userServise) {
        this.roleServise = roleServise;
        this.userServise = userServise;
    }


    @GetMapping("/")
    public String allPeople(Model model) {
        model.addAttribute("people", userServise.allUsers());
        return "/allPeople";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", roleServise.getAllRoles());
        return "/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userServise.addUsers(user);
        return "redirect:/admin/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userServise.findUserById(id));
        model.addAttribute("roles", roleServise.getAllRoles());
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userServise.update(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userServise.deleteUser(id);
        return "redirect:/admin/";
    }
}
