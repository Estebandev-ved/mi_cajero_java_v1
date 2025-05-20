package com.bancobit.atm.entity;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    private LocalDateTime fecha;
    private TipoMovimiento tipo;
    @Enumerated(EnumType.STRING)
    private double monto;
    @ManyToMany
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

}   
