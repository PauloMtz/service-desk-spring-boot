package com.sistema.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.domain.OrdemServico;
import com.sistema.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
    
    @Autowired
    private OrdemServicoRepository repository;

    public void salvar(OrdemServico ordemServico) {
        repository.save(ordemServico);
    }

    public OrdemServico buscarPorId(Long id) {
        Optional<OrdemServico> registroEncontrado = repository.findById(id);

        if (registroEncontrado.isPresent()) {
            return registroEncontrado.get();
        } else {
            return null;
        }
    }
}
