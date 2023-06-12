package com.app.api.atividade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade,Long> {

    Page<Atividade> findByProfessor(Pageable page, Long professor);

    List<Atividade> findAllByProfessorIn(List<Long> professor);
}
