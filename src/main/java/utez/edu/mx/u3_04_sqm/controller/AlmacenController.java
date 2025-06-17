package utez.edu.mx.u3_04_sqm.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.u3_04_sqm.entity.Almacen;
import utez.edu.mx.u3_04_sqm.services.AlmacenService;

import java.util.List;

@RestController
@RequestMapping("/almacenes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AlmacenController {

    private final AlmacenService almacenService;

    @GetMapping
    public ResponseEntity<List<Almacen>> getAllAlmacenes() {
        List<Almacen> almacenes = almacenService.findAll();
        return ResponseEntity.ok(almacenes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Almacen> getAlmacenById(@PathVariable Long id) {
        return almacenService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Almacen> createAlmacen(@Valid @RequestBody Almacen almacen) {
        Almacen savedAlmacen = almacenService.save(almacen);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAlmacen);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Almacen> updateAlmacen(@PathVariable Long id, @Valid @RequestBody Almacen almacen) {
        try {
            Almacen updatedAlmacen = almacenService.update(id, almacen);
            return ResponseEntity.ok(updatedAlmacen);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlmacen(@PathVariable Long id) {
        try {
            almacenService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}