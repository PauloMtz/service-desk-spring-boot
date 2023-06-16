package com.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Usuario findByEmail(String email);
}
