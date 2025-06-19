package utez.edu.mx.u3_04_sqm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.u3_04_sqm.entity.Cliente;
import utez.edu.mx.u3_04_sqm.exception.ResourceNotFoundException;
import utez.edu.mx.u3_04_sqm.repository.ClienteRepository;
import utez.edu.mx.u3_04_sqm.services.interfaces.ClienteInterface;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ClienteService implements ClienteInterface {
    private final ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        log.debug("Obteniendo todos los clientes");
        return clienteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findById(Long id) {
        log.debug("Buscando cliente por ID: {}", id);
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        log.debug("Guardando nuevo cliente: {}", cliente);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente update(Long id, Cliente cliente) {
        log.debug("Actualizando cliente con ID: {}", id);
        return clienteRepository.findById(id)
                .map(existingCliente -> {
                    existingCliente.setNombreCompleto(cliente.getNombreCompleto());
                    existingCliente.setNumeroTelefono(cliente.getNumeroTelefono());
                    existingCliente.setCorreoElectronico(cliente.getCorreoElectronico());
                    return clienteRepository.save(existingCliente);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Eliminando cliente con ID: {}", id);
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
