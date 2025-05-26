package com.bancobit.atm.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bancobit.atm.entity.Cuenta;
import com.bancobit.atm.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuenta(Cuenta cuenta);
    List<Movimiento> findByCuenta_NumeroOrderByFechaDesc(Cuenta cuenta);
}
