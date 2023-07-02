package com.sistema.domain;

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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cancelamentos")
public class Cancelamento {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informe o motivo do cancelamento")
	private String motivo;

	private String observacao;
    
    @Column(name = "data_cancelamento")
	private LocalDateTime dataCancelamento;

    @OneToOne
    @JoinColumn(name = "recebimento_id")
	private Recebimento recebimento;

    @OneToOne
    @JoinColumn(name = "usuario_id")
	private Usuario usuario;
}
