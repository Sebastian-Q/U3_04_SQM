package utez.edu.mx.u3_04_sqm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utez.edu.mx.u3_04_sqm.entity.Sede;

import java.util.List;
import java.util.Optional;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Long> {

    Optional<Sede> findByClave(String clave);

    @Query("SELECT s FROM Sede s WHERE s.state = :estado")
    List<Sede> findByEstado(@Param("estado") String estado);

    @Query("SELECT s FROM Sede s WHERE s.state = :estado AND s.municipio = :municipio")
    List<Sede> findByEstadoAndMunicipio(@Param("estado") String estado, @Param("municipio") String municipio);
}
