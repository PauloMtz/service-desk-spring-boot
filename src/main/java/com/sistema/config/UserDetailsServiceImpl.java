package com.sistema.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistema.domain.Usuario;
import com.sistema.repository.UsuarioRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario user = repository.findByEmail(email);

        if (email == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + email);
        }
    
        return new User(user.getUsername(), user.getPassword(), true, true, 
            true, true, user.getAuthorities());
    }
    
}
