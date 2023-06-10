package com.sistema.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.domain.Perfil;
import com.sistema.domain.Usuario;
import com.sistema.repository.PerfilRepository;
import com.sistema.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping("/novo")
    public String cadastrar(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "admin/usuarios/form"; // template
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Usuario usuario, BindingResult result,
        Model model, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "admin/usuarios/form"; // template
        }

        // cria perfil ao salvar usuário
        Perfil perfil = perfilRepository.findByPerfil("USER");
        List<Perfil> perfis = new ArrayList<Perfil>();
        perfis.add(perfil);
        usuario.setPerfis(perfis);

        // cria senha ao salvar usuário
        String encrypt = new BCryptPasswordEncoder().encode("123");
        usuario.setPassword(encrypt);

        repository.save(usuario);
        attr.addFlashAttribute("success", "Usuário registrado com sucesso.");
        return "redirect:/usuario/listar"; // rota
    }

    @GetMapping("/listar")
    public String listaUsuarios(Model model) {
        model.addAttribute("usuarios", repository.findAll());
        return "admin/usuarios/lista"; // template
    }

    @GetMapping("/excluir/{id}")
    public String deleteUsuario(@PathVariable("id") Long id, Model model,
        RedirectAttributes attr) {

        Usuario usuario = repository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));

        repository.delete(usuario);
        attr.addFlashAttribute("success", "Usuário excluído com sucesso.");
        return "redirect:/usuario/listar"; // rota
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model) {
        Optional<Usuario> usuarioEncontrado = repository.findById(id);

        if (!usuarioEncontrado.isPresent()) {
            throw new IllegalArgumentException("Usuário inválido: " + id);
        }

        Usuario usuario = usuarioEncontrado.get();
        model.addAttribute("usuario", usuario);
        model.addAttribute("listaPerfis", perfilRepository.findAll());
        return "admin/usuarios/form-edit"; // template
    }

    @PostMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") Long id, Model model,
        @RequestParam(value = "pps", required = false) int[] pps,
        @Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
        // esse pps recebe os checkboxes do formulário

        if (result.hasErrors()) {
            usuario.setId(id);
            return "admin/usuarios/form-edit"; // template
        }

        if (pps == null) {
            usuario.setId(id);
            attr.addFlashAttribute("erroPapel", "Selecione pelo menos um perfil");
            return "redirect:/usuario/editar/" + id;
        } else {
            // pega a lista de perfis selecionados
            List<Perfil> perfis = getPerfis(pps);

            Optional<Usuario> usuarioOptional = repository.findById(id);
            if (usuarioOptional.isPresent()) {
                Usuario usr = usuarioOptional.get();
                usr.setNome(usuario.getNome());
                usr.setEmail(usuario.getEmail());
                usr.setPerfis(perfis);
                usr.setAtivo(usuario.isAtivo());

                repository.save(usr);
                attr.addFlashAttribute("success", "Usuário atualizado com sucesso.");
            }

            return "redirect:/usuario/listar"; // rota
        }
    }

    @GetMapping("/alterasenha/{email}")
    public String alterarSenhaUsuario(@PathVariable("email") String email, Model model) {
        Usuario usuarioEncontrado = repository.findByEmail(email);

        if (usuarioEncontrado.equals(null)) {
            throw new IllegalArgumentException("Usuário inválido: " + email);
        }

        Usuario usuario = usuarioEncontrado;
        model.addAttribute("usuario", usuario);
        return "admin/usuarios/altera-senha"; // template
    }

    @PostMapping("/alterasenha/{email}")
    public String alterarSenhaUsuario(@PathVariable("email") String email, Model model,
        Usuario usuario, RedirectAttributes attr) {

        Usuario usr = repository.findByEmail(email);
        String encrypt = new BCryptPasswordEncoder().encode(usuario.getPassword());
        usr.setPassword(encrypt);

        attr.addFlashAttribute("success", "Senha alterada com sucesso.");
        repository.save(usr);
        
        return "redirect:/"; // rota
    }

    private List<Perfil> getPerfis(int[] pps) {
        List<Perfil> perfis = new ArrayList<Perfil>();
        for (int i = 0; i < pps.length; i++) {
            long idPerfil = pps[i];
            Optional<Perfil> perfilOptional = perfilRepository.findById(idPerfil);
            if (perfilOptional.isPresent()) {
                Perfil perfil = perfilOptional.get();
                perfis.add(perfil);
            }
        }
        return perfis;
    }
}
