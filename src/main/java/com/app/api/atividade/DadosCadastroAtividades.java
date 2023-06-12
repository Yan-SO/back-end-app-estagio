package com.app.api.atividade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAtividades(
        @NotBlank
        String titulo,
        @NotBlank
        String pergunta,
        @NotBlank
        String resposta,
        @NotBlank
        String alternativa1,
        @NotBlank
        String alternativa2,
        @NotBlank
        String alternativa3,
        @NotNull
        Long professor) {
}
