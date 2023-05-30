package com.app.api.atividades.respondidas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "atividades_respondidas")
@Entity(name = "atividadeRespondida")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class AtividadeRespondida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private boolean alunoResposta;
    private Date quando;

    private Long aluno;
    private Long professor;
    private Long atividade;
}