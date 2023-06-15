package com.app.api.relacionamentos.alunoAtividade;

import com.app.api.atividade.Atividade;

import java.util.Date;

public record DadosRetornorAtividadesRespondidas(Long id,boolean resposta, Date data, String titulo,String nome, Long aluno, Long Atividade) {
    public DadosRetornorAtividadesRespondidas(AtividadeAlunos dados,String titulo,String nome){
        this(dados.getId(),dados.isAlunoResposta(),dados.getQuando(),titulo,nome,dados.getAluno(),dados.getAtividade());
    }
}
