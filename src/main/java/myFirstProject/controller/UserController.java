package myFirstProject.controller;

import myFirstProject.model.User;
import myFirstProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "users";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        if (user.getId() == null) {
            userService.saveUser(user); // Добавление
        } else {
            userService.updateUser(user); // Редактирование
        }
        return "redirect:/users";
    }
    @GetMapping("/edit")
    public String editUser(@RequestParam Long id, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", userService.getUserById(id));
        return "users";
    }
    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
