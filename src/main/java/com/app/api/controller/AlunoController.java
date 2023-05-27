package com.app.api.controller;

import com.app.api.aluno.*;
import com.app.api.professor.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public Page<DadosRetornoAluno> listarAlunos(Pageable page){
        return alunoRepository.findAll(page).map(DadosRetornoAluno::new);
    }

    @GetMapping("/login")
    public DadosAluno loginAluno(@RequestBody DadosLoginAluno dados){
        List<Aluno> retorno = alunoRepository.findByRA(dados.RA());
        if(retorno.size() == 0){
            return new DadosLoginAluno("err", null);
        }else {
            if(retorno.get(0).getSenha().equals(dados.senha())){
                return  new DadosRetornoAluno(retorno.get(0));
            } else {
                return new DadosLoginAluno(dados.RA(), "err");
            }
        }
    }


    @PostMapping
    @Transactional
    public DadosRetornoAluno cadastrarAluno(@RequestBody DadosCadastroAluno dados){
        String emailProfessor = null;
        String RA = null;
        String email = null;

        List<Professor> prof = professorRepository.findByEmail(dados.emailProfessor());
        List<Aluno> alunoRA = alunoRepository.findByRA(dados.RA());
        List<Aluno> alunoEmail = alunoRepository.findByEmail(dados.email());

        if(prof.isEmpty()) emailProfessor = "err";
        if (!alunoRA.isEmpty()) RA = "err";
        if(!alunoEmail.isEmpty()) email = "err";


        var dadosErro = new DadosRetornoAluno(null, RA, null, null,email,emailProfessor);

        if (dadosErro.temErro()) return dadosErro;
        else {
            Aluno alunoCadastrado = new Aluno(dados, prof.get(0).getId());
            alunoRepository.save(alunoCadastrado);
            return new DadosRetornoAluno(alunoCadastrado);
        }
    }
    @DeleteMapping("/id={id}")
    @Transactional
    public void deletarAluno(@PathVariable Long id){
        alunoRepository.deleteById(id);
    }

}
