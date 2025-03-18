package main.ClassManger3.Controller;

import main.ClassManger3.Entity.UserEntity;
import main.ClassManger3.Repo.CourceRepo;
import main.ClassManger3.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class loginController {


//    @GetMapping("/login")
//    public String login(Model model) {
//        return "login";
//    }

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserEntity userDto = new UserEntity();
        model.addAttribute("user", userDto);
        return "registration";
    }
}
