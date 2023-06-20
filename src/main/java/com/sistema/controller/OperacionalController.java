package com.sistema.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.domain.OrdemServico;
import com.sistema.domain.Recebimento;
import com.sistema.domain.Usuario;
import com.sistema.enums.Status;
import com.sistema.repository.UsuarioRepository;
import com.sistema.service.ClienteService;
import com.sistema.service.OrdemServicoService;
import com.sistema.service.RecebimentoService;

@Controller
@RequestMapping("/operacao")
public class OperacionalController {

    @Autowired
    private RecebimentoService recebimentoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private OrdemServicoService ordemServicoService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping("/receber")
    public ModelAndView cadastrar() {
        var mv = new ModelAndView("operacoes/recebimento"); // template
        mv.addObject("recebimento", new Recebimento());
        mv.addObject("clientes", clienteService.lista());
        return mv;
    }

    @PostMapping("/receber")
    public String cadastrar(@Valid Recebimento recebimento, BindingResult result,
            Model model, RedirectAttributes attr) throws IOException {

        if (result.hasErrors()) {
            return "operacoes/recebimento"; // template
        }

        Recebimento recNumSerie = recebimentoService.buscarNumSerie(recebimento.getNumSerie());

        if (recNumSerie != null) {
            model.addAttribute("numSerieExiste", "Número de série já cadastrado no sistema");
            return "operacoes/recebimento"; // template
        }

        Authentication usuarioLogado = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getName());
        recebimento.setUsuario(usuario);
        recebimento.setStatus(Status.RECEBE_EQUIPAMENTO);
        recebimento.setDataRecebimento(LocalDateTime.now());
        recebimentoService.salvar(recebimento);
        attr.addFlashAttribute("success", "Recebimento efetuado com sucesso");
        return "redirect:/"; // rota
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        return listaPaginada(model, 1);
    }

    @GetMapping("/listar/{pageNumber}")
    public String listaPaginada(Model model,
        @PathVariable(value = "pageNumber") int currentPage) {

        Page<Recebimento> page = recebimentoService.listarTodos(currentPage);
        List<Recebimento> recebimentos = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        String url = "/operacao/listar/";
        String pag = "";

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("url", url);
        model.addAttribute("pag", pag);
        model.addAttribute("recebimentos", recebimentos);

        return "operacoes/recebimentos"; // template
    }

    @GetMapping("/buscar")
    public String buscarCliente(String nome, Model model) {
        return buscaPaginada(model, nome, 1);
    }

    @GetMapping("/buscar/{pageNumber}")
	public String buscaPaginada(Model model, @RequestParam("nome") String nome,
        @PathVariable(value = "pageNumber") int currentPage) {

		Page<Recebimento> page = recebimentoService.buscarPorCliente(nome, currentPage);
        List<Recebimento> recebimentos = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        String url = "/operacao/buscar/";
        String pag = "?nome=" + nome;

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("url", url);
        model.addAttribute("pag", pag);
        model.addAttribute("recebimentos", recebimentos);

        return "operacoes/recebimentos"; // template
	}

    @GetMapping("/detalhes/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes attr) {
        Recebimento registroEncontrado = recebimentoService.buscarPorId(id);

        if (registroEncontrado == null) {
            attr.addFlashAttribute("error", "Registro não encontrado");
            return "redirect:/operacao/listar"; // rota
        }

        model.addAttribute("recebimento", registroEncontrado);
        return "operacoes/detalhes"; // template
    }

    @GetMapping("/criar_os/{id}")
    public String criarOs(@PathVariable Long id, Model model, RedirectAttributes attr) {
        Recebimento registroEncontrado = recebimentoService.buscarPorId(id);

        if (registroEncontrado == null) {
            attr.addFlashAttribute("error", "Registro não encontrado");
            return "redirect:/operacao/listar"; // rota
        }

        OrdemServico ordemServico = new OrdemServico();
        ordemServico.setRecebimento(registroEncontrado);

        model.addAttribute("ordemServico", ordemServico);
        return "operacoes/abertura-os"; // template
    }

    @PostMapping("/criar_os/{id}")
    public String criarOs(@Valid OrdemServico ordemServico, BindingResult result,
            Model model, RedirectAttributes attr, @PathVariable("id") Long id) 
            throws IOException {

        if (result.hasErrors()) {
            return "operacoes/abertura-os"; // template
        }

        // pega id do recebimento
        Recebimento recebimento = recebimentoService.buscarPorId(id);
        var recebimentoId = recebimento.getId();

        // gera um número de OS com data invertida e id
        LocalDateTime data = LocalDateTime.now();
		String dataFormatadaOs = data.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        var numeroOs = dataFormatadaOs + recebimentoId;

        // pega o usuário logado e salva os dados da OS
        Authentication usuarioLogado = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findByEmail(usuarioLogado.getName());
        ordemServico.setNumeroOs(numeroOs);
        ordemServico.setUsuario(usuario);
        ordemServico.setDataOrdemServico(LocalDateTime.now());
        ordemServicoService.salvar(ordemServico);

        // atualiza status do recebimento
        recebimento.setStatus(Status.ABRE_ORDEM_SERVICO);
        recebimentoService.salvar(recebimento);

        // redireciona para página inicial com mensagem de sucesso
        attr.addFlashAttribute("success", "Ordem de serviço registrada com sucesso");
        return "redirect:/"; // rota
    }
}
