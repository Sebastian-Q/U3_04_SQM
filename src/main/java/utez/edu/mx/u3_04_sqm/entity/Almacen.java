package utez.edu.mx.u3_04_sqm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utez.edu.mx.u3_04_sqm.enums.TamanoAlmacen;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "almacenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String clave;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;

    @NotNull(message = "El precio de venta es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio de venta debe ser mayor a 0")
    @Column(name = "precio_venta", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El tamaño es obligatorio")
    @Column(nullable = false)
    private TamanoAlmacen tamano;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sede_id", nullable = false)
    @NotNull(message = "La sede es obligatoria")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sede sede;

    @PrePersist
    public void inicializar() {
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDate.now();
        }
        if (this.clave == null && this.sede != null) {
            this.clave = String.format("%s-A%d", this.sede.getClave(), this.id != null ? this.id : 0);
        }
    }
}
