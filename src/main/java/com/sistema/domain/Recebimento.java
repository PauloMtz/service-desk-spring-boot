package com.sistema.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.sistema.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "recebimentos")
public class Recebimento {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Informe o equipamento")
	@Size(min = 3, max = 60, message = "O campo nome deve ter entre {min} e {max} caracteres.")
    @Column(name = "equipamento", nullable = false, length = 60)
    private String equipamento;

    @Column(name = "marca", length = 60)
    private String marca;

    @Column(name = "modelo", length = 60)
    private String modelo;

    @NotBlank(message = "Informe o número de série")
    @Column(name = "num_serie", unique = true, nullable = false, length = 20)
    private String numSerie;

    @Column(name = "data_recebimento")
    private LocalDateTime dataRecebimento;

    private String observacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne(mappedBy = "recebimento")
    private Atendimento atendimento;

    @OneToOne(mappedBy = "recebimento")
    private Cancelamento cancelamento;
}
