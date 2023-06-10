package com.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.domain.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    
    // tem que ser o mesmo atributo da classe
    Perfil findByPerfil(String perfil);
}
