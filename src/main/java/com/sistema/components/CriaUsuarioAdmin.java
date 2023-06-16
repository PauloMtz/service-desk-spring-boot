package com.sistema.components;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.sistema.domain.Perfil;
import com.sistema.domain.Usuario;
import com.sistema.repository.PerfilRepository;
import com.sistema.repository.UsuarioRepository;

@Component
public class CriaUsuarioAdmin implements CommandLineRunner {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public void run(String... args) throws Exception {

        /*String email = "admin@mail.com";

        Usuario usuario = repository.findByEmail(email);

        // cria usuário ao rodar a aplicação se não existir no banco
        if (usuario == null) {
            usuario = new Usuario();
            usuario.setEmail("admin@mail.com");
            usuario.setNome("Maria Rita de Souza");
            usuario.setEmail(email);
            usuario.setAtivo(true);
            // cria perfil
            Perfil perfil = perfilRepository.findByPerfil("ADMIN");
            List<Perfil> perfis = new ArrayList<Perfil>();
            perfis.add(perfil);
            usuario.setPerfis(perfis);
            // cria senha
            String encrypt = new BCryptPasswordEncoder().encode("123");
            usuario.setPassword(encrypt);
            repository.save(usuario);
            System.out.println("*** Usuário criado: e-mail " + usuario.getEmail() + " e senha 123");
        }*/
    }
}
