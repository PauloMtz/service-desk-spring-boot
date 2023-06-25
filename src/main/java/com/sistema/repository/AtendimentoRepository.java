package com.sistema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.domain.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    @Query("SELECT at FROM Atendimento at JOIN at.recebimento r "
        + "WHERE r.status = 'EFETUA_ATENDIMENTO'")
    Page<Atendimento> listarAtendimentos(Pageable pageable);
}
