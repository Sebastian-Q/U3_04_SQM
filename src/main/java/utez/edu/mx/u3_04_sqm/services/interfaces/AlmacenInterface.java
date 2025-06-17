package utez.edu.mx.u3_04_sqm.services.interfaces;

import utez.edu.mx.u3_04_sqm.entity.Almacen;

import java.util.List;
import java.util.Optional;

public interface AlmacenInterface {
    List<Almacen> findAll();

    Optional<Almacen> findById(Long id);

    Almacen save(Almacen almacen);

    Almacen update(Long id, Almacen almacen);

    void deleteById(Long id);
}
