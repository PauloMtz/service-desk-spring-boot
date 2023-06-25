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
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "devolucoes")
public class Devolucao {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Informe o valor do serviço")
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    @Column(columnDefinition = "DECIMAL(9, 2) DEFAULT 0.00")
	private BigDecimal valor;

	private String observacao;

    @Column(name = "data_devolucao")
	private LocalDateTime dataDevolucao;

    @OneToOne
    @JoinColumn(name = "recebimento_id")
	private Recebimento recebimento;
    
    @OneToOne
    @JoinColumn(name = "usuario_id")
	private Usuario usuario;
}
