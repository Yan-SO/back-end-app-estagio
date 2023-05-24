package com.app.api.professor;

public record DadosRetornoProfessor(Long id,String nome, String email, String senha) implements DadosProfessor {
    public DadosRetornoProfessor(Professor professor) {
        this(professor.getId(), professor.getNome(), professor.getEmail(), professor.getSenha());
    }
}
