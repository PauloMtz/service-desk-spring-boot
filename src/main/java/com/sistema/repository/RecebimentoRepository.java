package com.sistema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.domain.Recebimento;

public interface RecebimentoRepository extends JpaRepository<Recebimento, Long> {

    Recebimento findByNumSerie(String numSerie);

    // join tables
    @Query("SELECT r FROM Recebimento r JOIN r.cliente c "
        + "WHERE UPPER(c.nome) LIKE CONCAT('%', UPPER(?1), '%') "
        + "AND status = 'RECEBE_EQUIPAMENTO'")
    Page<Recebimento> findByCliente(String nome, Pageable pageable);

    @Query("SELECT r FROM Recebimento r WHERE status = 'RECEBE_EQUIPAMENTO'")
    Page<Recebimento> listarRecebimentos(Pageable pageable);
}
