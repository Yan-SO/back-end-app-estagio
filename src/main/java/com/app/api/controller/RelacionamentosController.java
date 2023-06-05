package com.app.api.controller;

import com.app.api.aluno.Aluno;
import com.app.api.aluno.AlunoRepository;
import com.app.api.aluno.DadosRetornoAluno;
import com.app.api.professor.Professor;
import com.app.api.professor.ProfessorRepository;
import com.app.api.relacionamentos.AlunoProfessorRepository;
import com.app.api.relacionamentos.AlunosProfessores;
import com.app.api.relacionamentos.DadosCadastroAlunoProfRelacionamento;
import com.app.api.relacionamentos.DadosRetornoAlunoProfCadastro;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/relacionamento")
public class RelacionamentosController {


    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private AlunoProfessorRepository alunoProfessorRepository;

    @GetMapping("/meus-alunos/professor={id}")
    public Page<DadosRetornoAluno> listarMeusAlunos(Pageable page, @PathVariable Long id){
        List<AlunosProfessores> idAlunos= alunoProfessorRepository.findByProfessor(id);
        List<Aluno> alunos =alunoRepository.findAllById(idAlunos.stream().map(AlunosProfessores::getAluno).collect(Collectors.toList()));
        return new PageImpl<>(alunos,page,alunos.size()).map(DadosRetornoAluno::new);
    }
    @PostMapping("/add-aluno")
    @Transactional
    public DadosRetornoAlunoProfCadastro adicionarAluno(@RequestBody DadosCadastroAlunoProfRelacionamento dados){
        List<Professor> prof  = professorRepository.findByEmail(dados.emailProfessor());
        List<Aluno> aluno = alunoRepository.findByRA(dados.alunoRA());




            if(aluno.isEmpty() && !prof.isEmpty()){
                return new DadosRetornoAlunoProfCadastro("err", dados.emailProfessor());
            }else if(!aluno.isEmpty() && prof.isEmpty()){
                return new DadosRetornoAlunoProfCadastro(dados.alunoRA(), "err");
            }else if(aluno.isEmpty() && prof.isEmpty()){
                return new DadosRetornoAlunoProfCadastro("err", "err");
            }else {
                List<AlunosProfessores> copias= alunoProfessorRepository.findByAlunoAndProfessor(aluno.get(0).getId(), prof.get(0).getId());
                if (copias.isEmpty()){
                    alunoProfessorRepository.save(new AlunosProfessores(prof.get(0).getId(), aluno.get(0).getId()));
                    return new DadosRetornoAlunoProfCadastro(dados.alunoRA(), dados.emailProfessor());
                }else {
                    return new  DadosRetornoAlunoProfCadastro("full","full");
                }

            }


    }
}
