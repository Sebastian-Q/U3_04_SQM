package utez.edu.mx.u3_04_sqm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.u3_04_sqm.entity.Almacen;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Long> {
}
