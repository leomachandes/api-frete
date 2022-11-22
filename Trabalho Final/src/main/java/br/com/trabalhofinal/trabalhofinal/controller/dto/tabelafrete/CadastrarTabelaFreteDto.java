package br.com.trabalhofinal.trabalhofinal.controller.dto.tabelafrete;

import br.com.trabalhofinal.trabalhofinal.controller.validation.bigdecimal.BigDecimalValid;
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
public class CadastrarTabelaFreteDto implements Serializable {

    @NotEmpty(message = "A descrição precisa ser informada")
    private String descricao;

    @BigDecimalValid(message = "O valor do Km percorrido precisa ser informado")
    private BigDecimal valorKmPercorrido;

    @BigDecimalValid(message = "A taxa de administração do frete precisa ser informada")
    private BigDecimal taxaAdministracao;

}
