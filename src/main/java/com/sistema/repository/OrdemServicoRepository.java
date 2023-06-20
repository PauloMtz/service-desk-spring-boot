package com.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.domain.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
}
