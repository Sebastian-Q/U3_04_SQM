package utez.edu.mx.u3_04_sqm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.u3_04_sqm.entity.Sede;
import utez.edu.mx.u3_04_sqm.exception.ResourceNotFoundException;
import utez.edu.mx.u3_04_sqm.repository.SedeRepository;
import utez.edu.mx.u3_04_sqm.services.interfaces.SedeInterface;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SedeService implements SedeInterface {
    private final SedeRepository sedeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Sede> findAll() {
        log.debug("Obteniendo todas las sedes");
        return sedeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Sede> findById(Long id) {
        log.debug("Buscando sede por ID: {}", id);
        return sedeRepository.findById(id);
    }

    @Override
    public Sede save(Sede sede) {
        log.debug("Guardando nueva sede: {}", sede);
        return sedeRepository.save(sede);
    }

    @Override
    public Sede update(Long id, Sede sede) {
        log.debug("Actualizando sede con ID: {}", id);
        return sedeRepository.findById(id)
                .map(existingSede -> {
                    existingSede.setEstado(sede.getEstado());
                    existingSede.setMunicipio(sede.getMunicipio());
                    return sedeRepository.save(existingSede);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Sede no encontrada con ID: " + id));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Eliminando sede con ID: {}", id);
        if (!sedeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Sede no encontrada con ID: " + id);
        }
        sedeRepository.deleteById(id);
    }
}
