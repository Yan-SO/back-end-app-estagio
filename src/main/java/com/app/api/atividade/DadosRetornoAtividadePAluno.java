package com.app.api.atividade;

import java.util.Date;

public record DadosRetornoAtividadePAluno(Long id, String titulo, String pergunta, String resposta, Date data, String alternativa1, String alternativa2, String alternativa3, String professor) {
    public DadosRetornoAtividadePAluno(Atividade atividade, String professor) {
        this(atividade.getId(), atividade.getTitulo(), atividade.getPergunta(), atividade.getResposta(),atividade.getCriado() , atividade.getAlternativa1(), atividade.getAlternativa2(), atividade.getAlternativa3(), professor);
    }
}
