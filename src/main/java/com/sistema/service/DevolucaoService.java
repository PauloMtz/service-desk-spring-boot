package com.sistema.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sistema.domain.Devolucao;
import com.sistema.repository.DevolucaoRepository;

@Service
public class DevolucaoService {

    @Autowired
    private DevolucaoRepository repository;

    public void salvar(Devolucao devolucao) {
        repository.save(devolucao);
    }

    public Page<Devolucao> listarTodos(int numPage) {
        int size = 5;
        Pageable pageable = PageRequest.of(numPage -1, size, Sort.by("dataAtendimento"));
        return repository.listarDevolucoes(pageable);
    }

    public Devolucao buscarPorId(Long id) {
        Optional<Devolucao> registroEncontrado = repository.findById(id);

        if (registroEncontrado.isPresent()) {
            return registroEncontrado.get();
        } else {
            return null;
        }
    }
}
