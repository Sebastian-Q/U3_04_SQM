package utez.edu.mx.u3_04_sqm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Entity
@Table(name = "sedes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String clave;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false)
    private String estado;

    @NotBlank(message = "El municipio es obligatorio")
    @Column(nullable = false)
    private String municipio;

    @PrePersist
    public void generarClave() {
        if (this.clave == null) {
            String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
            String aleatorio = String.format("%04d", new Random().nextInt(10000));
            this.clave = String.format("C%d-%s-%s", this.id != null ? this.id : 0, fecha, aleatorio);
        }
    }
}
