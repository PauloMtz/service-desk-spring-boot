package com.sistema.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.domain.Cliente;
import com.sistema.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var mv = new ModelAndView("admin/clientes/form"); // template
        mv.addObject("cliente", new Cliente());
        return mv;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid Cliente cliente, BindingResult result,
            Model model, RedirectAttributes attr) throws IOException {

        if (result.hasErrors()) {
            return "admin/clientes/form"; // template
        }

        Cliente cliCpf = service.buscarPorCpf(cliente.getCpf());
        Cliente cliEmail = service.buscarPorEmail(cliente.getEmail());

        if (cliCpf != null) {
            model.addAttribute("cpfExiste", "CPF já cadastrado no sistema.");
            return "admin/clientes/form"; // template
        }

        if (cliEmail != null) {
            model.addAttribute("emailExiste", "E-mail já cadastrado no sistema.");
            return "admin/clientes/form"; // template
        }

        service.salvar(cliente);
        attr.addFlashAttribute("success", "Registro inserido com sucesso.");
        return "redirect:/cliente/listar"; // rota
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        return listaPaginada(model, 1);
    }

    @GetMapping("/listar/{pageNumber}")
    public String listaPaginada(Model model,
        @PathVariable(value = "pageNumber") int currentPage) {

        Page<Cliente> page = service.listarTodos(currentPage);
        List<Cliente> clientes = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        String url = "/cliente/listar/";
        String pag = "";

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("url", url);
        model.addAttribute("pag", pag);
        model.addAttribute("clientes", clientes);

        return "admin/clientes/lista"; // template
    }

    @GetMapping("/buscar")
    public String getPorNome(String nome, Model model) {
        return buscaPaginada(model, nome, 1);
    }

    
    @GetMapping("/buscar/{pageNumber}")
    public String buscaPaginada(Model model, @RequestParam("nome") String nome,
        @PathVariable(value = "pageNumber") int currentPage) {

        Page<Cliente> page = service.buscarPorNome(nome, currentPage);
        List<Cliente> clientes = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        String url = "/cliente/buscar/";
        String pag = "?nome=" + nome;

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("url", url);
        model.addAttribute("pag", pag);
        model.addAttribute("clientes", clientes);

        return "admin/clientes/lista"; // template
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, RedirectAttributes attr) {
        service.excluir(id);
        attr.addFlashAttribute("success", "Registro excluído com sucesso.");
        return "redirect:/cliente/listar"; // rota
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes attr) {
        Cliente clienteEncontrado = service.buscarPorId(id);

        if (clienteEncontrado == null) {
            attr.addFlashAttribute("error", "Cliente não encontrado");
            return "redirect:/cliente/listar"; // rota
        }

        model.addAttribute("cliente", clienteEncontrado);
        return "admin/clientes/form"; // template
    }

    @PostMapping("/editar/{id}")
    public String editar(@Valid @ModelAttribute("cliente") Cliente cliente, Model model,
            @PathVariable("id") Long id, BindingResult result, RedirectAttributes attr) throws IOException {

        if (result.hasErrors()) {
            cliente.setId(id);
            return "admin/clientes/form"; // template
        }

        Cliente cliCpf = service.buscarPorCpf(cliente.getCpf());
        Cliente cliEmail = service.buscarPorEmail(cliente.getEmail());

        if (cliCpf != null && cliCpf.getId() != id) {
            attr.addFlashAttribute("cpfExiste", "CPF já cadastrado no sistema.");
            return "redirect:/cliente/editar/" + id; // rota
        }

        if (cliEmail != null && cliEmail.getId() != id) {
            attr.addFlashAttribute("emailExiste", "E-mail já cadastrado no sistema.");
            return "redirect:/cliente/editar/" + id; // rota
        }

        service.salvar(cliente);
        attr.addFlashAttribute("success", "Registro atualizado com sucesso.");
        return "redirect:/cliente/listar"; // rota
    }
}
