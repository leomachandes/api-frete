package br.com.trabalhofinal.trabalhofinal.controller.dto.tabelafrete;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AlterarTabelaFreteDto implements Serializable {

    @NotEmpty(message = "A descrição precisa ser informada")
    private String descricao;

    @NotNull(message = "O valor do Km percorrido precisa ser informado")
    private BigDecimal valorKmPercorrido;

    @NotNull(message = "A taxa de administração do frete precisa ser informada")
    private BigDecimal taxaAdministracao;
}
