package br.com.trabalhofinal.trabalhofinal.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "entrega")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrega implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "descricaoCarga", nullable = false)
    private  String descricaoCarga;

    @Enumerated(EnumType.STRING)
    private StatusEntrega statusEntrega;

    @Column(name = "cepOrigemEntrega", nullable = false)
    private  String cepOrigemEntrega;

    @Column(name = "cepOrigemDestino", nullable = false)
    private  String cepOrigemDestino;

    @Column(name = "distanciaKm", nullable = false)
    private BigDecimal distanciaKm;

    @Column(name = "vlTotalEntrega", nullable = false)
    private BigDecimal vlTotalEntrega;

//    @ManyToOne
//    @JoinColumn(name = "idTabelafrete")
//    private TabelaFrete tabelaFrete;
//
//    @ManyToOne
//    @JoinColumn(name = "idEntregador")
//    private Entregador entregador;



}
