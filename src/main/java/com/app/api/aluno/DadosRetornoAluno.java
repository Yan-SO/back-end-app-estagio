package com.app.api.aluno;

public record DadosRetornoAluno(Long id,String RA, String nome, String senha, String email) implements DadosAluno {
    public DadosRetornoAluno(Aluno aluno)  {
        this(aluno.getId(), aluno.getRA(), aluno.getNome(), aluno.getSenha(), aluno.getEmail());
    }

    public boolean temErro() {
        if(RA != null) return true;
        if (nome != null) return true;
        if (senha != null) return true;
        if (email != null) return true;

        return false;
    }
}
