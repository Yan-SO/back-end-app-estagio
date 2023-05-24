package com.app.api.professor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor,Long> {
    List<Professor> findByEmail(String email);
}
