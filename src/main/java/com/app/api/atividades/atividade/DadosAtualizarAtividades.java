package com.app.api.atividades.atividade;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizarAtividades(
        @NotNull
        Long id,
        String titulo,
        String pergunta,
        String resposta,
        String alternativa1,
        String alternativa2,
        String alternativa3) {
}
