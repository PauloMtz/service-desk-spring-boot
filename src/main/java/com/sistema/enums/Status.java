package com.sistema.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    RECEBE_EQUIPAMENTO("Equipamento Recebido"), // quando recebe
    ABRE_ORDEM_SERVICO("OS Aberta"), // quando abre OS
    EFETUA_ATENDIMENTO("OS Atendida"), // quando atende OS
    CANCELA_ATENDIMENTO("Atendimento Cancelado"), // quando atende OS ou recebimento
    DEVOLVE_EQUIPAMENTO("Equipamento Devolvido"); // quando devolve

    private String descricao;
}
