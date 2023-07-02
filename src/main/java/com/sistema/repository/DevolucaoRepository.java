package com.sistema.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.domain.Devolucao;
import com.sistema.domain.Recebimento;

public interface DevolucaoRepository extends JpaRepository<Devolucao, Long> {

    // https://codejava.net/frameworks/spring/jpa-join-query-for-like-search-examples

    @Query("SELECT r FROM Recebimento r "
        + "WHERE r.status = 'EFETUA_ATENDIMENTO' "
        + "OR r.status = 'CANCELA_ATENDIMENTO'")
    Page<Devolucao> listarLiberados(Pageable pageable);

    @Query("SELECT r FROM Recebimento r JOIN r.atendimento at JOIN r.cancelamento cc "
        + "WHERE r.id = ?1")
    Optional<Recebimento> buscarPorId(Long id);

    @Query("SELECT dev FROM Devolucao dev JOIN dev.recebimento r "
        + "WHERE r.status = 'DEVOLVE_EQUIPAMENTO'")
    Page<Devolucao> listarDevolucoes(Pageable pageable);
}
