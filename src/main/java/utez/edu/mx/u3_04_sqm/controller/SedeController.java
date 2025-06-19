package utez.edu.mx.u3_04_sqm.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.u3_04_sqm.entity.Sede;
import utez.edu.mx.u3_04_sqm.services.SedeService;

import java.util.List;

@RestController
@RequestMapping("/sedes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SedeController {

    private final SedeService sedeService;

    @GetMapping
    public ResponseEntity<List<Sede>> getAllSedes() {
        List<Sede> sedes = sedeService.findAll();
        return ResponseEntity.ok(sedes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sede> getSedeById(@PathVariable Long id) {
        return sedeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Sede> createSede(@Valid @RequestBody Sede sede) {
        Sede savedSede = sedeService.save(sede);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSede);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sede> updateSede(@PathVariable Long id, @Valid @RequestBody Sede sede) {
        try {
            Sede updatedSede = sedeService.update(id, sede);
            return ResponseEntity.ok(updatedSede);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSede(@PathVariable Long id) {
        try {
            sedeService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
