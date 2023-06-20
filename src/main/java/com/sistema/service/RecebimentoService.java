package com.sistema.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sistema.domain.Recebimento;
import com.sistema.repository.RecebimentoRepository;

@Service
public class RecebimentoService {
    
    @Autowired
    private RecebimentoRepository repository;

    public void salvar(Recebimento recebimento) {
        repository.save(recebimento);
    }

    public Page<Recebimento> listarTodos(int numPage) {
        int size = 5;
        Pageable pageable = PageRequest.of(numPage -1, size, Sort.by("dataRecebimento"));
        return repository.listarTodos(pageable);
    }

    public Page<Recebimento> buscarPorCliente(String nome, int numPage) {
        int size = 5;
        Pageable pageable = PageRequest.of(numPage -1, size, Sort.by("id"));
        return repository.findByCliente(nome, pageable);
    }

    public Recebimento buscarPorId(Long id) {
        Optional<Recebimento> registroEncontrado = repository.findById(id);

        if (registroEncontrado.isPresent()) {
            return registroEncontrado.get();
        } else {
            return null;
        }
    }

    public Recebimento buscarNumSerie(String numSerie) {
        Recebimento recebimento = repository.findByNumSerie(numSerie);
        return recebimento;
    }
}
