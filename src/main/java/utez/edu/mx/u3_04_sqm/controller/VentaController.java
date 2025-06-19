package utez.edu.mx.u3_04_sqm.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.u3_04_sqm.dto.VentaRequest;
import utez.edu.mx.u3_04_sqm.entity.Venta;
import utez.edu.mx.u3_04_sqm.services.VentaService;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VentaController {

    private final VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> getAllVentas() {
        List<Venta> ventas = ventaService.findAll();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        return ventaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venta> realizarVenta(@Valid @RequestBody VentaRequest ventaRequest) {
        Venta venta = ventaService.realizarVenta(ventaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(venta);
    }
}
