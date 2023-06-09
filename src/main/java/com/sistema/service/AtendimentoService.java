package com.sistema.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sistema.domain.Atendimento;
import com.sistema.repository.AtendimentoRepository;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository repository;

    public void salvar(Atendimento atendimento) {
        repository.save(atendimento);
    }

    public Page<Atendimento> listarTodos(int numPage) {
        int size = 5;
        Pageable pageable = PageRequest.of(numPage -1, size, Sort.by("dataAtendimento"));
        return repository.listarAtendimentos(pageable);
    }

    public Atendimento buscarPorId(Long id) {
        Optional<Atendimento> registroEncontrado = repository.findById(id);

        if (registroEncontrado.isPresent()) {
            return registroEncontrado.get();
        } else {
            return null;
        }
    }
}
