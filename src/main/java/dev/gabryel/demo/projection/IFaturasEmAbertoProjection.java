package dev.gabryel.demo.projection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IFaturasEmAbertoProjection {

    Long getMatriculaId();
    String getAlunoNome();
    LocalDateTime getDataVencimento();
    BigDecimal getQuantidade();

}
