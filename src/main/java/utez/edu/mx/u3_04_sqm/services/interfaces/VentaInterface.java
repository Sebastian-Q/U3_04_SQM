package utez.edu.mx.u3_04_sqm.services.interfaces;

import utez.edu.mx.u3_04_sqm.dto.VentaRequest;
import utez.edu.mx.u3_04_sqm.entity.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaInterface {
    List<Venta> findAll();
    Optional<Venta> findById(Long id);
    Venta realizarVenta(VentaRequest ventaRequest);
}
