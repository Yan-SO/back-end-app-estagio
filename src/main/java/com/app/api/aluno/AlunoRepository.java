package com.app.api.aluno;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno,Long> {
    List<Aluno> findByRA(String RA);

    List<Aluno> findByEmail(String email);

}
