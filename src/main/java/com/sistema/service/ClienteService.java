package com.sistema.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sistema.domain.Cliente;
import com.sistema.repository.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository repository;

    public void salvar(Cliente cliente) {
        repository.save(cliente);
    }

    public void excluir(Long id) {
        var clienteEncontrado = buscarPorId(id);
        repository.delete(clienteEncontrado);
    }

    public Page<Cliente> listarTodos(int numPage) {
        int size = 5;
        Pageable pageable = PageRequest.of(numPage -1, size, Sort.by("nome"));
        return repository.findAll(pageable);
    }

    public Page<Cliente> buscarPorNome(String nome, int numPage) {
        int size = 5;
        Pageable pageable = PageRequest.of(numPage -1, size, Sort.by("nome"));
        return repository.findByNome(nome, pageable);
    }

    public Cliente buscarPorId(Long id) {
        Optional<Cliente> clienteEncontrado = repository.findById(id);

        if (clienteEncontrado.isPresent()) {
            return clienteEncontrado.get();
        } else {
            return null;
        }
    }

    public Cliente buscarPorCpf(String cpf) {
        Cliente cliente = repository.findByCpf(cpf);
        return cliente;
    }

    public Cliente buscarPorEmail(String email) {
        Cliente cliente = repository.findByEmail(email);
        return cliente;
    }
}
