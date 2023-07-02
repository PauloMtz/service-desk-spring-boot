package com.sistema.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sistema.domain.Devolucao;
import com.sistema.domain.Recebimento;
import com.sistema.repository.DevolucaoRepository;

@Service
public class DevolucaoService {

    @Autowired
    private DevolucaoRepository repository;

    public void salvar(Devolucao devolucao) {
        repository.save(devolucao);
    }

    public Page<Devolucao> listarLiberados(int numPage) {
        int size = 5;
        Pageable pageable = PageRequest.of(numPage -1, size, Sort.by("id"));
        return repository.listarLiberados(pageable);
    }

    public Recebimento buscarPorId(Long id) {
        Optional<Recebimento> registroEncontrado = repository.buscarPorId(id);

        if (registroEncontrado.isPresent()) {
            return registroEncontrado.get();
        } else {
            return null;
        }
    }
}
