package com.sistema.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {
   
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O campo nome deve ser preenchido.")
	@Size(min = 3, max = 60, message = "O campo nome deve ter entre {min} e {max} caracteres.")
    @Column(name = "nome", nullable = false, length = 60)
    private String nome;

    @CPF(message = "CPF inválido")
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @NotNull(message = "Informe a data de nascimento")
    @PastOrPresent(message = "A data de nascimento deve ser menor ou igual à data de hoje.")
    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "data_nascimento", nullable = false, columnDefinition = "DATE")
    private LocalDate dataNascimento;

    @NotNull(message = "Informe o sexo")
    @Column(nullable = false, length = 1)
    private String sexo;

    @NotEmpty(message = "O campo e-mail deve ser preenchido")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotEmpty(message = "Informe um telefone")
    @Column(nullable = false, length = 15)
    private String telefone;

    // valida os atributos de endereço
    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToOne(mappedBy = "cliente")
    private Recebimento recebimento;
}
