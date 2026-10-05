package dev.gabryel.demo.repository;

import dev.gabryel.demo.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IAlunoRespository extends JpaRepository<Aluno, Long>, JpaSpecificationExecutor<Aluno> {
    boolean existsByEmail(String email);
}
