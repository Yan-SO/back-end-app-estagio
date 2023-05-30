package com.app.api.atividades.atividade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade,Long> {

    Page<Atividade> findByProfessor(Pageable page, Long professor);
}
