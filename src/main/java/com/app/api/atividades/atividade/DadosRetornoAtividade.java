package com.app.api.atividades.atividade;

import java.util.Date;

public record DadosRetornoAtividade(Long id, String titulo, String pergunta, String resposta, Date data, String alternativa1, String alternativa2, String alternativa3, Long professor) {

    public DadosRetornoAtividade(Atividade atividade) {
        this(atividade.getId(), atividade.getTitulo(), atividade.getPergunta(), atividade.getResposta(),atividade.getCriado() , atividade.getAlternativa1(), atividade.getAlternativa2(), atividade.getAlternativa3(), atividade.getProfessor());
    }
}
