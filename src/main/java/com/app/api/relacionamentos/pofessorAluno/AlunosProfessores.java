package com.app.api.relacionamentos.pofessorAluno;

import com.app.api.aluno.Aluno;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "professores_alunos")
@Entity(name = "AlunosProfessores")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AlunosProfessores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long aluno;
    private Long professor;


    public AlunosProfessores(Long profId, Long alunoId) {
        this.aluno = alunoId;
        this.professor = profId;
    }
}
