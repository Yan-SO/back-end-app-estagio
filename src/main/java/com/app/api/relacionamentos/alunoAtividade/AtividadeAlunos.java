package com.app.api.relacionamentos.alunoAtividade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "atividades_alunos")
@Entity(name = "AtividadeAlunos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class AtividadeAlunos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean alunoResposta;
    private Date quando;

    private Long aluno;
    private Long atividade;

    public AtividadeAlunos(DadosCadastroAtiviRespon dados, Date data){
        this.quando=data;
        this.aluno = dados.aluno();
        this.atividade = dados.atividade();
        this.alunoResposta = dados.alunoResposta();
    }

}