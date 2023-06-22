package com.sistema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sistema.domain.Atendimento;
import com.sistema.domain.OrdemServico;
import com.sistema.repository.AtendimentoRepository;
import com.sistema.repository.OrdemServicoRepository;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository repository;
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public void salvar(Atendimento atendimento) {
        repository.save(atendimento);
    }

    public Page<OrdemServico> listarTodos(int numPage) {
        int size = 5;
        Pageable pageable = PageRequest.of(numPage -1, size, Sort.by("dataOrdemServico"));
        return ordemServicoRepository.listarOrdensServico(pageable);
    }
}
