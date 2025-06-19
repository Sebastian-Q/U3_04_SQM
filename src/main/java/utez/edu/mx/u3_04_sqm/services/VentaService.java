package utez.edu.mx.u3_04_sqm.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.u3_04_sqm.dto.VentaRequest;
import utez.edu.mx.u3_04_sqm.entity.Almacen;
import utez.edu.mx.u3_04_sqm.entity.Cliente;
import utez.edu.mx.u3_04_sqm.entity.Venta;
import utez.edu.mx.u3_04_sqm.exception.ResourceNotFoundException;
import utez.edu.mx.u3_04_sqm.repository.AlmacenRepository;
import utez.edu.mx.u3_04_sqm.repository.ClienteRepository;
import utez.edu.mx.u3_04_sqm.repository.VentaRepository;
import utez.edu.mx.u3_04_sqm.services.interfaces.VentaInterface;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class VentaService implements VentaInterface {
    private final VentaRepository ventaRepository;
    private final AlmacenRepository almacenRepository;
    private final ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Venta> findAll() {
        log.debug("Obteniendo todas las ventas");
        return ventaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> findById(Long id) {
        log.debug("Buscando venta por ID: {}", id);
        return ventaRepository.findById(id);
    }

    @Override
    public Venta realizarVenta(VentaRequest ventaRequest) {
        log.debug("Realizando venta: {}", ventaRequest);

        // Verificar que el almacén existe
        Almacen almacen = almacenRepository.findById(ventaRequest.getAlmacenId())
                .orElseThrow(() -> new ResourceNotFoundException("Almacén no encontrado con ID: " + ventaRequest.getAlmacenId()));

        if (!almacen.getAvailable()) {
            throw new ResourceNotFoundException("El almacén no está disponible para venta. Ya ha sido vendido.");
        }

        // Verificar que el cliente existe
        Cliente cliente = clienteRepository.findById(ventaRequest.getClienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + ventaRequest.getClienteId()));

        // Crear la venta
        Venta venta = new Venta();
        venta.setAlmacen(almacen);
        venta.setCliente(cliente);
        venta.setPrecioVenta(ventaRequest.getPrecioVenta());
        almacen.setAvailable(false);
        return ventaRepository.save(venta);
    }
}
