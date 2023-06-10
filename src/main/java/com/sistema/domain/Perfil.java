package com.sistema.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * @RequiredArgsConstructor gera construtor vazio
     * entra em conflito com @NoArgsConstructor
     * @NonNull acrescenta atributos no @RequiredArgsConstructor
     * Ver vídeo Youtube: https://www.youtube.com/watch?v=DMBvVfeSg4o&ab_channel=AlgaWorks
     */
    @NonNull
    private String perfil;

    // entidade fraca
    @ManyToMany(mappedBy = "perfis", fetch = FetchType.EAGER)
    private List<Usuario> usuarios;

    @Override
    public String getAuthority() {
        return perfil.toString();
    }
}
