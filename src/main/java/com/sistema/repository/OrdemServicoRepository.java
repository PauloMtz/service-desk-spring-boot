package com.sistema.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sistema.domain.OrdemServico;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    @Query("SELECT os FROM OrdemServico os JOIN os.recebimento r "
        + "WHERE r.status = 'ABRE_ORDEM_SERVICO'")
    Page<OrdemServico> listarOrdensServico(Pageable pageable);
}
