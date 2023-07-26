package com.sistema.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.domain.Cliente;
import com.sistema.service.ClienteService;
import com.sistema.service.JasperService;

@Controller
@RequestMapping("/relatorios")
public class JasperController {
    
    @Autowired
    private JasperService service;

    @Autowired
    private ClienteService clienteService;

    /*@GetMapping("/clientes")
    public String abrirRelatorio(Model model) {
        return "relatorios/clientes"; // template
    }*/

    @GetMapping("/clientes")
    public String listar(Model model) {
        return listaPaginada(model, 1);
    }

    @GetMapping("/clientes/{pageNumber}")
    public String listaPaginada(Model model,
        @PathVariable(value = "pageNumber") int currentPage) {

        Page<Cliente> page = clienteService.listarTodos(currentPage);
        List<Cliente> clientes = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        String url = "/relatorios/clientes/";
        String pag = "";

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("url", url);
        model.addAttribute("pag", pag);
        model.addAttribute("clientes", clientes);

        return "relatorios/clientes"; // template
    }

    // exibe relatório sem parâmetros
    @GetMapping("/relatorio/pdf/jr1")
    public void exibirRelatorio1(@RequestParam("code") String code,
        @RequestParam("acao") String acao, HttpServletResponse response) throws IOException {

        byte[] bytes = service.exportPDF(code);
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);

        // se acao for 'v' de visualizar no navegador...
        if (acao.equals("v")) {
            response.setHeader("Content-disposition", "inline; filename=relatorio-" + code + ".pdf");
        } else {
            // se não, baixa o relatório
            response.setHeader("Content-disposition", "attachment; filename=relatorio-" + code + ".pdf");
        }

        response.getOutputStream().write(bytes);
    }
}
