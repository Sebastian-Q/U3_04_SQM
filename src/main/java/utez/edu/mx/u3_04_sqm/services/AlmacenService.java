package utez.edu.mx.u3_04_sqm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.u3_04_sqm.entity.Almacen;
import utez.edu.mx.u3_04_sqm.enums.TamanoAlmacen;
import utez.edu.mx.u3_04_sqm.repository.AlmacenRepository;
import utez.edu.mx.u3_04_sqm.services.interfaces.AlmacenInterface;

import java.math.BigDecimal;
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
    @Transactional(readOnly = true)
    public Optional<Almacen> findByClave(String clave) {
        log.debug("Buscando almacén por clave: {}", clave);
        return almacenRepository.findByClave(clave);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Almacen> findBySedeId(Long sedeId) {
        log.debug("Buscando almacenes por sede ID: {}", sedeId);
        return almacenRepository.findBySedeId(sedeId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Almacen> findByTamano(TamanoAlmacen tamano) {
        log.debug("Buscando almacenes por tamaño: {}", tamano);
        return almacenRepository.findByTamano(tamano);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Almacen> findByPrecioVentaBetween(BigDecimal minPrecio, BigDecimal maxPrecio) {
        log.debug("Buscando almacenes por rango de precio: {} - {}", minPrecio, maxPrecio);
        return almacenRepository.findByPrecioVentaBetween(minPrecio, maxPrecio);
    }
}
