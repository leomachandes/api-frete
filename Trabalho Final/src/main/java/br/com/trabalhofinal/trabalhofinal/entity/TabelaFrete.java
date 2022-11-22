package br.com.trabalhofinal.trabalhofinal.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "frete")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TabelaFrete implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "vlKmPercorrido", nullable = false)
    private BigDecimal vlKmPercorrido;

    @Column(name = "taxaAdm", nullable = false)
    private BigDecimal taxaAdministracao;





}
