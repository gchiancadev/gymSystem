package dev.gabryel.demo.specification;

import dev.gabryel.demo.domain.Aluno;
import dev.gabryel.demo.dto.AlunoFiltroRequest;
import org.springframework.data.jpa.domain.Specification;

public class AlunoSpecification {

    public static Specification<Aluno> comfiltro(AlunoFiltroRequest filtro){
        return Specification
                .where(nomeContem(filtro.nome()))
                .and(emailContem(filtro.email()))
                .and(celularContem(filtro.celular()))
                .and(estadoContem(filtro.estado()));
    }

    private static Specification<Aluno> nomeContem(String nome) {
        return (root, query, cb) -> {
            if (nome == null || nome.isBlank()){
                return null;
            }
            return cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
        };
    }
    private static Specification<Aluno> emailContem(String email) {
        return (root, query, cb) -> {
            if (email == null || email.isBlank()) {
                return null;
            }
            return cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
        };
    }
        private static Specification<Aluno> celularContem (String celular){
            return (root, query, cb) -> {
                if (celular == null || celular.isBlank()) {
                    return null;
                }
                return cb.like(cb.lower(root.get("celular")), "%" + celular.toLowerCase() + "%");
            };
        }
    private static Specification<Aluno> estadoContem (String estado){
        return (root, query, cb) -> {
            if (estado == null || estado.isBlank()) {
                return null;
            }
            return cb.like(cb.lower(root.get("endereco").get("estado")), estado.toUpperCase());
        };
    }
    }