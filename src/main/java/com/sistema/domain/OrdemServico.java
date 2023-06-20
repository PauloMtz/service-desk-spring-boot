package com.sistema.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ordens_servico")
public class OrdemServico {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_os")
	private String numeroOs;

    @Column(name = "data_ordem_servico")
	private LocalDateTime dataOrdemServico;

    @NotBlank(message = "Informe o campo descrição")
	private String descricao;

    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    @Column(columnDefinition = "DECIMAL(9, 2) DEFAULT 0.00")
	private BigDecimal valor;

	private String observacao;

    @OneToOne
    @JoinColumn(name = "tecnico_id")
	private Usuario usuario;
	
    @OneToOne
    @JoinColumn(name = "recebimento_id")
	private Recebimento recebimento;
}
