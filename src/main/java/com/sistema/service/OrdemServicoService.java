package com.sistema.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<OrdemServico> listarTodos(int numPage) {
        int size = 5;
        Pageable pageable = PageRequest.of(numPage -1, size, Sort.by("dataOrdemServico"));
        return repository.listarOrdensServico(pageable);
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
