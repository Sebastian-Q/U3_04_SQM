package utez.edu.mx.u3_04_sqm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utez.edu.mx.u3_04_sqm.entity.Cliente;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByCorreoElectronico(String correoElectronico);

    Optional<Cliente> findByNumeroTelefono(String numeroTelefono);

    @Query("SELECT c FROM Cliente c WHERE c.nombreCompleto LIKE %:nombre%")
    List<Cliente> findByNombreCompletoContaining(@Param("nombre") String nombre);
}
