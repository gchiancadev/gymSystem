package dev.gabryel.demo.doc;

import dev.gabryel.demo.dto.AlunoFiltroRequest;
import dev.gabryel.demo.dto.AlunoRequest;
import dev.gabryel.demo.dto.AlunoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Aluno",
        description = "Operações para cadastro, consulta, atualização, exclusão" +
                      " e filtragem de alunos"
)
public interface IAlunoControllerDoc {
    @Operation(
            summary = "Cadastrar aluno",
            description = "Cria um novo aluno no sistema de academia",
            responses = {
                    @ApiResponse(
                        responseCode = "201",
                            description = "Aluno cadastrado com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação ou erro de negócio",
                            content = @Content(schema = @Schema(implementation = Error.class))
                    )
            }
    )
    AlunoResponse cadastrar
            (
                @RequestBody
                @Valid
                @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Dados necessários para cadastrar um aluno",
                        required = true,
                        content = @Content(schema = @Schema(implementation = AlunoRequest.class),
                        examples = @ExampleObject(
                                name = "Aluno válido",
                                value = """
                                        {
                                        "nome": "João Teste",
                                          "dataNascimento": "1998-05-15",
                                          "sexo": "M",
                                          "telefone": "48334412345",
                                          "celular": "48999164543",
                                          "email": "joao.teste@email.com",
                                          "observacao": "aluno iniciante",
                                          "endereco": {
                                            "rua": "Rua das Flores",
                                            "numero": "123",
                                            "complemento": "apartamento 202",
                                            "bairro": "centro",
                                            "cidade": "Criciúma",
                                            "estado": "SC",
                                            "cep": "88801-000"
                                          }
                                        """

                                ))
                )
                AlunoRequest alunoRequest
            );
    @Operation(
            summary = "Listar alunos",
            description = "Lista alunos de forma paginada, permitindo filtros opcionais por"+
                    "nome, e-mail, celular, cidade estado",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de alunos retornada com sucesso")
            }
    )
    Page<AlunoResponse> listar(
            @Parameter(description = "Filtros opcionais para busca de alunos")
            AlunoFiltroRequest filtro,
            @Parameter(description = "Informações de paginação e ordenação")
            Pageable pageable
    );
}



