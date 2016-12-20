package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;
import java.util.*;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        signupRepository.save(new Signup(name, address));
        return "redirect:/done?name=" + name;
    }
    
    @RequestMapping(value = "/done", method = RequestMethod.GET)
    public String done(Model model, @RequestParam String name) {
        List<Signup> signups = signupRepository.findAll();
        List<String> names = new ArrayList<String>();
        for(Signup s : signups) {
            names.add(s.getName());
        }
        model.addAttribute("name", name);
        model.addAttribute("names", names);
        return "done";
    }
    
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Model model) {
        List<Signup> signups = signupRepository.findAll();
        List<String> names = new ArrayList<String>();
        for(Signup s : signups) {
            names.add(s.getName());
        }
        model.addAttribute("names", names);
        return "view";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin() {
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String user, @RequestParam String pass) {
        if(user.equals("admin") && pass.equals("admin123")) {
            return "redirect:/adminview";
        } else {
            return "login";
        }
    }
    
    @RequestMapping(value = "/adminview", method = RequestMethod.GET)
    public String adminview(Model model) {
        List<Signup> signups = signupRepository.findAll();
        List<String> adminlist = new ArrayList<String>();
        for(Signup s : signups) {
            adminlist.add(s.getName() + " - " + s.getAddress());
        }
        model.addAttribute("adminlist", adminlist);
        return "adminview";
    }
}
