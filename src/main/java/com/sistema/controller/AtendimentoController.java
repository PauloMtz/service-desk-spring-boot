package com.sistema.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.domain.Atendimento;
import com.sistema.domain.OrdemServico;
import com.sistema.domain.Recebimento;
import com.sistema.domain.Usuario;
import com.sistema.enums.Status;
import com.sistema.repository.UsuarioRepository;
import com.sistema.service.AtendimentoService;
import com.sistema.service.OrdemServicoService;
import com.sistema.service.RecebimentoService;

@Controller
@RequestMapping("/atendimento")
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private RecebimentoService recebimentoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/listar")
    public String listar(Model model) {
        return listaPaginada(model, 1);
    }

    @GetMapping("/listar/{pageNumber}")
    public String listaPaginada(Model model,
        @PathVariable(value = "pageNumber") int currentPage) {

        Page<OrdemServico> page = ordemServicoService.listarTodos(currentPage);
        List<OrdemServico> ordenServicos = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        String url = "/atendimento/listar/";
        String pag = "";

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("url", url);
        model.addAttribute("pag", pag);
        model.addAttribute("ordenServicos", ordenServicos);

        return "atendimentos/ordens-servicos"; // template
    }

    @GetMapping("/detalhes/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes attr) {
        OrdemServico registroEncontrado = ordemServicoService.buscarPorId(id);

        if (registroEncontrado == null) {
            attr.addFlashAttribute("error", "Registro não encontrado");
            return "redirect:/atendimento/listar"; // rota
        }

        model.addAttribute("os", registroEncontrado);
        return "atendimentos/detalhes"; // template
    }

    @GetMapping("/atender_os/{id}")
    public String atenderOs(@PathVariable Long id, Model model, RedirectAttributes attr) {
        OrdemServico registroEncontrado = ordemServicoService.buscarPorId(id);

        if (registroEncontrado == null) {
            attr.addFlashAttribute("error", "Registro não encontrado");
            return "redirect:/atendimento/listar"; // rota
        }
        
        Atendimento atendimento = new Atendimento();
        atendimento.setOrdemServico(registroEncontrado);

        model.addAttribute("atendimento", atendimento);
        return "atendimentos/atender-os"; // template
    }

    @PostMapping("/atender_os/{id}")
    public String atenderOs(@Valid Atendimento atendimento, BindingResult result,
            Model model, RedirectAttributes attr, @PathVariable("id") Long id) 
            throws IOException {

        if (result.hasErrors()) {
            return "atendimento/atender-os"; // template
        }

        // pega id da OS
        OrdemServico ordemServico = ordemServicoService.buscarPorId(id);
        Recebimento recebimento = ordemServico.getRecebimento();

        // pega o usuário logado e salva os dados da OS
        Authentication usuarioLogado = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getName());
        atendimento.setUsuario(usuario);
        atendimento.setDataAtendimento(LocalDateTime.now());
        atendimento.setOrdemServico(ordemServico);
        atendimento.setRecebimento(ordemServico.getRecebimento());
        atendimentoService.salvar(atendimento);

        // atualiza status do recebimento
        recebimento.setStatus(Status.EFETUA_ATENDIMENTO);
        recebimentoService.salvar(recebimento);

        // redireciona para lista com mensagem de sucesso
        attr.addFlashAttribute("success", "Ordem de serviço registrada com sucesso");
        return "redirect:/atendimento/listar"; // rota
    }
}
