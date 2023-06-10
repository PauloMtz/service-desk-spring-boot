package com.sistema.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sistema.domain.Perfil;
import com.sistema.repository.PerfilRepository;

@Component
public class CriaPerfis implements CommandLineRunner {

    @Autowired
    private PerfilRepository repository;

    @Override
    public void run(String... args) throws Exception {
        
        String[] perfis = {"ADMIN", "USER", "ATEND"};

        for (String perfilString : perfis) {
            Perfil perfil = repository.findByPerfil(perfilString);

            if (perfil == null) {
                perfil = new Perfil(perfilString);
                repository.save(perfil);
            }
        }

        System.out.println("*** Perfis criados");
    } 
}
