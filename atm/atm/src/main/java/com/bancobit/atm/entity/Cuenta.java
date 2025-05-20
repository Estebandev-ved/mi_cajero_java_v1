package com.bancobit.atm.entity;
import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String numero;
    private double saldo;
    private TipoCuenta tipo;
    @Enumerated(EnumType.STRING)
    private Cuenta cliente;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private List<Movimiento> movimientos;

}
