package com.app.api.relacionamentos.alunoAtividade;

import com.app.api.relacionamentos.pofessorAluno.AlunosProfessores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadesRespondidasRepository extends JpaRepository<AtividadeAlunos,Long> {
    List<AtividadeAlunos> findByAtividade(Long atividade);

    List<AtividadeAlunos> findByAluno(Long aluno);

    List<AlunosProfessores> findByAlunoAndAtividade(Long aluno, Long atividade);
}
