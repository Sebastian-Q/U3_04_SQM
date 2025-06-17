package utez.edu.mx.u3_04_sqm.services.interfaces;

import utez.edu.mx.u3_04_sqm.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteInterface {
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Optional<Cliente> findByCorreoElectronico(String correoElectronico);
    List<Cliente> findByNombreCompletoContaining(String nombre);
    Cliente save(Cliente cliente);
    Cliente update(Long id, Cliente cliente);
    void deleteById(Long id);
}
