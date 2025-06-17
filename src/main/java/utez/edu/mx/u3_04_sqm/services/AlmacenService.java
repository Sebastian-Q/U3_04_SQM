package utez.edu.mx.u3_04_sqm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.u3_04_sqm.entity.Almacen;
import utez.edu.mx.u3_04_sqm.exception.ResourceNotFoundException;
import utez.edu.mx.u3_04_sqm.repository.AlmacenRepository;
import utez.edu.mx.u3_04_sqm.services.interfaces.AlmacenInterface;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AlmacenService implements AlmacenInterface {
    private final AlmacenRepository almacenRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Almacen> findAll() {
        log.debug("Obteniendo todos los almacenes");
        return almacenRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Almacen> findById(Long id) {
        log.debug("Buscando almacén por ID: {}", id);
        return almacenRepository.findById(id);
    }

    @Override
    public Almacen save(Almacen almacen) {
        log.debug("Guardando nuevo almacén: {}", almacen);
        return almacenRepository.save(almacen);
    }

    @Override
    public Almacen update(Long id, Almacen almacen) {
        log.debug("Actualizando almacén con ID: {}", id);
        return almacenRepository.findById(id)
                .map(existingAlmacen -> {
                    existingAlmacen.setPrecioVenta(almacen.getPrecioVenta());
                    existingAlmacen.setPrecioRenta(almacen.getPrecioRenta());
                    existingAlmacen.setTamano(almacen.getTamano());
                    existingAlmacen.setSede(almacen.getSede());
                    return almacenRepository.save(existingAlmacen);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Almacén no encontrado con ID: " + id));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Eliminando almacén con ID: {}", id);
        if (!almacenRepository.existsById(id)) {
            throw new ResourceNotFoundException("Almacén no encontrado con ID: " + id);
        }
        almacenRepository.deleteById(id);
    }
}
