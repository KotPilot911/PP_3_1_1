package mvc.controller;

import mvc.entity.User;
import mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String showAllUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        model.addAttribute("users", userList);
        System.out.println(userList);
        return "all-users";
    }
    @RequestMapping("/addNewUser")
    public String addNewUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }
    @RequestMapping("/saveUser")
    public String saveUser(@ModelAttribute ("user") User user) {
        userService.saveUser(user);
        return "redirect:/";
    }
    @RequestMapping("/updateInfo/{id}")
    public String updateUser(@PathVariable("id") int id, Model model){
        User user = userService.getUser(id);
        System.out.println(user);
        model.addAttribute("user", user);
        return "user-info";
    }
    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        userService.deleteUser(id);
        return "redirect:/";
    }


}
