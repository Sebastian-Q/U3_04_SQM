package utez.edu.mx.u3_04_sqm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utez.edu.mx.u3_04_sqm.entity.Almacen;
import utez.edu.mx.u3_04_sqm.enums.TamanoAlmacen;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Long> {

    Optional<Almacen> findByClave(String clave);

    List<Almacen> findBySedeId(Long sedeId);

    List<Almacen> findByTamano(TamanoAlmacen tamano);

    @Query("SELECT a FROM Almacen a WHERE a.precioVenta BETWEEN :minPrecio AND :maxPrecio")
    List<Almacen> findByPrecioVentaBetween(@Param("minPrecio") BigDecimal minPrecio, @Param("maxPrecio") BigDecimal maxPrecio);
}
