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
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "atendimentos")
public class Atendimento {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informe o campo descrição")
	private String descricao;

	private String observacao;
    
    @Column(name = "data_atendimento")
	private LocalDateTime dataAtendimento;

    @OneToOne
    @JoinColumn(name = "recebimento_id")
	private Recebimento recebimento;

    @OneToOne
    @JoinColumn(name = "ordem_servico_id")
    private OrdemServico ordemServico;

    @OneToOne
    @JoinColumn(name = "tecnico_id")
	private Usuario usuario;
}
