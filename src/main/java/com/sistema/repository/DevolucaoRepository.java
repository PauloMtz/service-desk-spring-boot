package com.sistema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.domain.Devolucao;

public interface DevolucaoRepository extends JpaRepository<Devolucao, Long> {

    @Query("SELECT dev FROM Devolucao dev JOIN dev.recebimento r "
        + "WHERE r.status = 'DEVOLVE_EQUIPAMENTO'")
    Page<Devolucao> listarDevolucoes(Pageable pageable);
}
