package com.sistema.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    RECEBE_EQUIPAMENTO("Equipamento Recebido"), // automático quando recebe
    CANCELA_ATENDIMENTO("Atendimento Cancelado"), // informa quando atende
    ABRE_ORDEM_SERVICO("OS Aberta"), // automático quando abre OS
    EFETUA_ATENDIMENTO("OS Atendida"), // informa quando atende
    CANCELA_OPERACAO("OS Cancelada"), // informa quando atende
    DEVOLVE_EQUIPAMENTO("Equipamento Devolvido"); // automático quando devolve

    private String descricao;
}
