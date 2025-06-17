package utez.edu.mx.u3_04_sqm.services.interfaces;

import utez.edu.mx.u3_04_sqm.entity.Sede;

import java.util.List;
import java.util.Optional;

public interface SedeInterface {
    List<Sede> findAll();
    Optional<Sede> findById(Long id);
    Sede save(Sede sede);
    Sede update(Long id, Sede sede);
    void deleteById(Long id);
}
