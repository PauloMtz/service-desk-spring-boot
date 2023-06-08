package com.sistema.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Preencha o campo CEP")
    @Column(name = "cep", nullable = false, length = 9)
    private String cep;
    
    @NotBlank(message = "Informe o endereço")
    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @Column(name = "complemento")
    private String complemento;

    @NotBlank(message = "Preencha o campo bairro")
    @Column(name = "bairro", nullable = false, length = 100)
    private String bairro;

    @NotBlank(message = "Preencha o campo cidade")
    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;

    @NotBlank(message = "Informe um estado")
    @Column(name = "estado", length = 2, nullable = false)
    private String uf;

    @OneToOne(mappedBy = "endereco")
    private Cliente cliente;
}
