package com.sistema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("msgHome", "Bem vindo(a) ao sistema!");
        return "home"; // template
    }

    @RequestMapping("/home")
    public String home(Model model) {
        return "redirect:/"; // rota
    }

    @RequestMapping("/login")
    public String login(Model model) {
        return "login"; // template
    }

    @RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginErro", "Credenciais inválidas");
		return "login";
	}
}
