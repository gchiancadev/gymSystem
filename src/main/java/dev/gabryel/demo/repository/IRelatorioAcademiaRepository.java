package dev.gabryel.demo.repository;


import dev.gabryel.demo.domain.FaturaMatricula;
import dev.gabryel.demo.projection.IAlunosPorCidadeProjection;
import dev.gabryel.demo.projection.IFaturamentoMensalProjection;
import dev.gabryel.demo.projection.IFaturasEmAbertoProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface IRelatorioAcademiaRepository extends Repository<FaturaMatricula,Long>{

    @Query(
            value = """
            SELECT 
                    TO_CHAR(data_vencimento,'YYYY-MM') 
                    AS mes,SUM(valor) as total
            FROM faturas_matriculas
            WHERE status = 'PAGA'
            GROUP BY TO_CHAR(data_vencimento,'YYYY-MM')
            ORDER BY mes
            """,
            nativeQuery= true
    )
    List<IFaturamentoMensalProjection> faturamentoMensal();


    @Query(
            value = """
            SELECT 
                    cidade,
                   count(*) as quantidade
            FROM alunos
            GROUP BY cidade
            ORDER BY quantidade
            """,
            nativeQuery= true
    )
    List<IAlunosPorCidadeProjection> alunosPorCidade();

    @Query(
            value = """

            SELECT a.nome AS aluno, f.valor AS valor, f.data_vencimento AS vencimento
            FROM faturas_matriculas f
            JOIN matriculas m ON m.id = f.matricula_id
            JOIN alunos a ON a.id = m.aluno_id
            WHERE f.status = 'ABERTA'
            ORDER BY f.data_vencimento DESC
            """,
            nativeQuery= true
    )
    List<IFaturasEmAbertoProjection> faturasEmAberto();
}
