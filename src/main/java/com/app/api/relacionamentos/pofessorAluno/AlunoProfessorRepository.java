package com.app.api.relacionamentos.pofessorAluno;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoProfessorRepository extends JpaRepository<AlunosProfessores, Long> {
    public List<AlunosProfessores> findByProfessor(Long id);
    public List<AlunosProfessores> findByAluno(Long id);
    List<AlunosProfessores> findByAlunoAndProfessor(Long aluno, Long professor);
}
