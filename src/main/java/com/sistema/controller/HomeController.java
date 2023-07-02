package com.sistema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sistema.repository.CancelamentoRepository;
import com.sistema.repository.OrdemServicoRepository;
import com.sistema.repository.RecebimentoRepository;

@Controller
public class HomeController {

    @Autowired
    private RecebimentoRepository rRepository;

    @Autowired
    private OrdemServicoRepository osRepository;

    @Autowired
    private CancelamentoRepository ccRepository;
    
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("recebimento", rRepository.countByEquipamento());
        model.addAttribute("liberado", rRepository.countById());
        model.addAttribute("os", osRepository.countById());
        model.addAttribute("cc", ccRepository.countById());
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
		model.addAttribute("loginErro", "Login inválido, verifique as credenciais");
		return "login";
	}
}
