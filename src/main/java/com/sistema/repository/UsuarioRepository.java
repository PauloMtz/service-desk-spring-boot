package com.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // verifica se já tem cadastro
    Usuario findByEmail(String email);
}
