package com.app.api.controller;

import com.app.api.aluno.Aluno;
import com.app.api.aluno.AlunoRepository;
import com.app.api.aluno.DadosRetornoAluno;
import com.app.api.atividade.Atividade;
import com.app.api.atividade.AtividadeRepository;
import com.app.api.atividade.DadosRetornoAtividade;
import com.app.api.atividade.DadosRetornoAtividadePAluno;
import com.app.api.professor.Professor;
import com.app.api.professor.ProfessorRepository;
import com.app.api.relacionamentos.alunoAtividade.AtividadeAlunos;
import com.app.api.relacionamentos.alunoAtividade.AtividadesRespondidasRepository;
import com.app.api.relacionamentos.alunoAtividade.DadosCadastroAtiviRespon;
import com.app.api.relacionamentos.alunoAtividade.DadosRetornorAtividadesRespondidas;
import com.app.api.relacionamentos.pofessorAluno.AlunoProfessorRepository;
import com.app.api.relacionamentos.pofessorAluno.AlunosProfessores;
import com.app.api.relacionamentos.pofessorAluno.DadosCadastroAlunoProfRelacionamento;
import com.app.api.relacionamentos.pofessorAluno.DadosRetornoProfAddAluno;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private AtividadeRepository atividadeRepository;
    @Autowired
    private AtividadesRespondidasRepository respondidasRepository;

    @GetMapping("/atividades-alunos={id}")
    public Page<DadosRetornoAtividadePAluno> listaAtividadesAluno(@PathVariable Long id, Pageable page){
        List<AlunosProfessores> lista =alunoProfessorRepository.findByAluno(id);

        List<Long> professoresIds = new ArrayList<>();
        for (AlunosProfessores alunoProfessor : lista) {
            Long professorId = alunoProfessor.getProfessor();
            professoresIds.add(professorId);
        }
        List<Atividade> atividades = atividadeRepository.findAllByProfessorIn(professoresIds);

        List<DadosRetornoAtividadePAluno> atividadesComProf = new ArrayList<>();
        for (Atividade atividade: atividades) {
            String nome= professorRepository.findById(atividade.getProfessor()).get().getNome();
            atividadesComProf.add(new DadosRetornoAtividadePAluno(atividade,nome));
        }
        return new PageImpl<>(atividadesComProf,page,atividadesComProf.size());
    }

    @GetMapping("/atividades-alunos/feitas={id}")
    public Page<DadosRetornorAtividadesRespondidas> listaAtividadesAlunoFeita(@PathVariable Long id, Pageable page){
        List<AtividadeAlunos> atividadeRespondidas = respondidasRepository.findByAluno(id);

        if (atividadeRespondidas.isEmpty()) return null;

        List<DadosRetornorAtividadesRespondidas> dadosRetorno = new ArrayList<>();
        for (AtividadeAlunos atividadeAlunos : atividadeRespondidas) {
            Optional<Atividade> atividade= atividadeRepository.findById(atividadeAlunos.getAtividade());
            String titulo = atividade.get().getTitulo();

            String nome = professorRepository.findById(atividade.get().getProfessor()).get().getNome();

            dadosRetorno.add(new DadosRetornorAtividadesRespondidas
                    (atividadeAlunos,titulo,nome));
        }
        return new PageImpl<>(dadosRetorno,page,dadosRetorno.size());
    }

    @GetMapping("/atividades-alunos/a-fazer={id}")
    public Page<DadosRetornoAtividade>  listaAtividadesAlunoAFazer(@PathVariable Long id, Pageable page){
        List<AlunosProfessores> lista =alunoProfessorRepository.findByAluno(id);

        List<Long> professoresIds = new ArrayList<>();
        for (AlunosProfessores alunoProfessor : lista) {
            Long professorId = alunoProfessor.getProfessor();
            professoresIds.add(professorId);
        }
        List<Atividade> atividades = atividadeRepository.findAllByProfessorIn(professoresIds);

        List<Atividade> atividadesAFazer = new ArrayList<>();
        for (Atividade atividade: atividades) {
            boolean tem = respondidasRepository.findByAlunoAndAtividade( id, atividade.getId()).isPresent();
            if(!tem) atividadesAFazer.add(atividade);
        }

        return new PageImpl<>(atividadesAFazer,page,atividadesAFazer.size()).map(DadosRetornoAtividade::new);
    }


    @PostMapping("/enviar-resposta")
    @Transactional
    public void responderAtividade(@RequestBody DadosCadastroAtiviRespon dados){
        respondidasRepository.save(new AtividadeAlunos(dados, new Date()));
    }



    @GetMapping("/meus-alunos/professor={id}")
    public Page<DadosRetornoAluno> listarMeusAlunos(Pageable page, @PathVariable Long id){
        List<AlunosProfessores> idAlunos= alunoProfessorRepository.findByProfessor(id);
        List<Aluno> alunos =alunoRepository.findAllById(idAlunos.stream().map(AlunosProfessores::getAluno).collect(Collectors.toList()));
        return new PageImpl<>(alunos,page,alunos.size()).map(DadosRetornoAluno::new);
    }
    @PostMapping("/add-aluno")
    @Transactional
    public DadosRetornoProfAddAluno adicionarAluno(@RequestBody DadosCadastroAlunoProfRelacionamento dados){
        List<Professor> prof  = professorRepository.findByEmail(dados.emailProfessor());
        List<Aluno> aluno = alunoRepository.findByRA(dados.alunoRA());

            if(aluno.isEmpty() && !prof.isEmpty()){
                return new DadosRetornoProfAddAluno("err", dados.emailProfessor());
            }else if(!aluno.isEmpty() && prof.isEmpty()){
                return new DadosRetornoProfAddAluno(dados.alunoRA(), "err");
            }else if(aluno.isEmpty() && prof.isEmpty()){
                return new DadosRetornoProfAddAluno("err", "err");
            }else {
                List<AlunosProfessores> copias= alunoProfessorRepository.findByAlunoAndProfessor(aluno.get(0).getId(), prof.get(0).getId());
                if (copias.isEmpty()){
                    alunoProfessorRepository.save(new AlunosProfessores(prof.get(0).getId(), aluno.get(0).getId()));
                    return new DadosRetornoProfAddAluno(aluno.get(0).getNome(), prof.get(0).getNome());
                }else {
                    return new DadosRetornoProfAddAluno("full","full");
                }
            }


    }

    @DeleteMapping
    @Transactional
    public String deletarRelacionamento(@RequestBody DadosRetornoProfAddAluno dados){
        List<Professor> prof  = professorRepository.findByEmail(dados.email());
        List<Aluno> aluno = alunoRepository.findByRA(dados.Ra());

        if (prof.size() == 1 && aluno.size() == 1){
            List<AlunosProfessores> id = alunoProfessorRepository.findByAlunoAndProfessor(aluno.get(0).getId(),prof.get(0).getId());
            if (id.isEmpty()) {
                return "err_relacionamento";
            }else {
                alunoProfessorRepository.deleteById(id.get(0).getId());
                return "deletado";
            }
        }
        return "err_professor_aluno_null";
    }
}
