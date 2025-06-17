package utez.edu.mx.u3_04_sqm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/almacenes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AlmacenController {
    //List<AlmacenDTO> findAll();

}
