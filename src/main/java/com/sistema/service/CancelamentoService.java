package com.sistema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.domain.Cancelamento;
import com.sistema.repository.CancelamentoRepository;

@Service
public class CancelamentoService {

    @Autowired
    private CancelamentoRepository repository;

    public void salvar(Cancelamento cancelamento) {
        repository.save(cancelamento);
    }
}
