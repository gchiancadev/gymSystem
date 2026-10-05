package dev.gabryel.demo.controller;

import dev.gabryel.demo.dto.AlunoFiltroRequest;
import dev.gabryel.demo.dto.AlunoRequest;
import dev.gabryel.demo.dto.AlunoResponse;
import dev.gabryel.demo.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping

    @ResponseStatus(HttpStatus.CREATED)
    public AlunoResponse cadastrar(@RequestBody @Valid AlunoRequest alunoRequest){
        return alunoService.cadatrar(alunoRequest);
    }

    @GetMapping
    public Page<AlunoResponse> listar(AlunoFiltroRequest filtro,Pageable pageable){
        return alunoService.listar(filtro,pageable);
    }
    @GetMapping("/{id}")
    public AlunoResponse buscarPorId(@PathVariable Long id){
        return alunoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AlunoResponse atualizar(@PathVariable Long id, @RequestBody AlunoRequest alunoRequest){
        return alunoService.atualizar(id, alunoRequest);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(Long id){
        alunoService.excluir(id);
    }
}
