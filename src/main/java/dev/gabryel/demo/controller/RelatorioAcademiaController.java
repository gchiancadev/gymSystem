package dev.gabryel.demo.controller;

import dev.gabryel.demo.projection.IAlunosPorCidadeProjection;
import dev.gabryel.demo.projection.IFaturamentoMensalProjection;
import dev.gabryel.demo.projection.IFaturasEmAbertoProjection;
import dev.gabryel.demo.repository.IRelatorioAcademiaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioAcademiaController {

    private final IRelatorioAcademiaRepository relatorioAcademia;

    public RelatorioAcademiaController(IRelatorioAcademiaRepository repository) {
        this.relatorioAcademia = repository;
    }

    @GetMapping("/faturamento-mensal")
    public List<IFaturamentoMensalProjection> faturamentoMensal(){
        return relatorioAcademia.faturamentoMensal();
    }

    @GetMapping("/alunos-por-cidade")
    public List<IAlunosPorCidadeProjection> alunosPorCidade(){
        return relatorioAcademia.alunosPorCidade();
    }

    @GetMapping("/faturas-em-aberto")
    public List<IFaturasEmAbertoProjection> faturasEmAberto(){
        return relatorioAcademia.faturasEmAberto();
    }
}
