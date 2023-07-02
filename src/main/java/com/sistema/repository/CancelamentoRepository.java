package com.sistema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.domain.Cancelamento;

public interface CancelamentoRepository extends JpaRepository<Cancelamento, Long> {

    @Query("SELECT cc FROM Cancelamento cc JOIN cc.recebimento r "
        + "WHERE r.status = 'CANCELA_ATENDIMENTO'")
    Page<Cancelamento> listarCancelamentos(Pageable pageable);

    @Query("SELECT count(*) FROM Cancelamento cc JOIN cc.recebimento r "
        + "WHERE r.status = 'CANCELA_ATENDIMENTO'")
    long countById();
}
