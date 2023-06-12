package com.app.api.relacionamentos.alunoAtividade;

import com.app.api.atividade.Atividade;

import java.util.Date;

public record DadosRetornorAtividadesRespondidas(boolean resposta, Date data, String titulo,String nome) {
}
