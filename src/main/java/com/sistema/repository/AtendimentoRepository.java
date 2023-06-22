package com.sistema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.domain.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
}
