package utez.edu.mx.u3_04_sqm.services.interfaces;

import utez.edu.mx.u3_04_sqm.entity.Almacen;
import utez.edu.mx.u3_04_sqm.enums.TamanoAlmacen;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AlmacenInterface {
    List<Almacen> findAll();
    Optional<Almacen> findById(Long id);
    Optional<Almacen> findByClave(String clave);
    List<Almacen> findBySedeId(Long sedeId);
    List<Almacen> findByTamano(TamanoAlmacen tamano);
    List<Almacen> findByPrecioVentaBetween(BigDecimal minPrecio, BigDecimal maxPrecio);
    Almacen save(Almacen almacen);
    Almacen update(Long id, Almacen almacen);
    void deleteById(Long id);
}
