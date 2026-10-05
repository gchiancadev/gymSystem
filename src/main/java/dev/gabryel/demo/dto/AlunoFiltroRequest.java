package dev.gabryel.demo.dto;

import dev.gabryel.demo.domain.enums.Sexo;

import java.time.LocalDate;

public record AlunoFiltroRequest(
        String nome,
        LocalDate dataNascimento,
        Sexo sexo,
        String telefone,
        String celular,
        String email,
        String observacao,
        String estado
) {
}
