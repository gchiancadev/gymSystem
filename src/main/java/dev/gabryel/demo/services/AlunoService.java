package dev.gabryel.demo.services;

import dev.gabryel.demo.domain.Aluno;
import dev.gabryel.demo.dto.AlunoFiltroRequest;
import dev.gabryel.demo.dto.AlunoRequest;
import dev.gabryel.demo.dto.AlunoResponse;
import dev.gabryel.demo.exception.RegraNegocioException;
import dev.gabryel.demo.repository.IAlunoRespository;
import dev.gabryel.demo.specification.AlunoSpecification;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final IAlunoRespository alunoRespository;

    public AlunoService(IAlunoRespository alunoRespository) {
        this.alunoRespository = alunoRespository;
    }

    @Transactional
    public AlunoResponse cadatrar(AlunoRequest alunoRequest) {
        if (alunoRequest.email() != null && alunoRespository.existsByEmail(alunoRequest.email())) {
            throw new RegraNegocioException("Já existe um aluno");
        }
        Aluno aluno = alunoRequest.toEntity();
        Aluno alunoSalvo = alunoRespository.save(aluno);
        return AlunoResponse.fromEntity(alunoSalvo);
    }

    public Page<AlunoResponse> listar(AlunoFiltroRequest filtro, Pageable pageable) {
        return alunoRespository.findAll(AlunoSpecification.comfiltro(filtro),pageable)
                .map(AlunoResponse::fromEntity);
    }
    public AlunoResponse buscarPorId(Long id){
        Aluno aluno = buscarEntidadePorId(id);
        return AlunoResponse.fromEntity(aluno);
    }

    public AlunoResponse atualizar(Long id, AlunoRequest alunoRequest){
        Aluno aluno = buscarEntidadePorId(id);
        alunoRequest.preencher(aluno);
        Aluno alunoAtualizado = alunoRespository.save(aluno);
        return AlunoResponse.fromEntity(alunoAtualizado);

    }
    private Aluno buscarEntidadePorId(Long id){
        return alunoRespository.findById(id).orElseThrow(() -> new RegraNegocioException("Aluno não encontrado"));
    }
    @Transactional
    public void excluir(Long id){
        Aluno aluno = buscarEntidadePorId(id);
        alunoRespository.delete(aluno);
    }
}
