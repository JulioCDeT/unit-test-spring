package io.juliocdet.unittesting.unittesting.controller;

import io.juliocdet.unittesting.unittesting.model.Mascota;
import io.juliocdet.unittesting.unittesting.repository.MascotasDao;
import io.juliocdet.unittesting.unittesting.service.MascotasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MascotasController {

    private final MascotasService service;

    @GetMapping("/mascotas")
    public ResponseEntity<?> getMascotas() {
        return service.getAllMascotas();
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> addMascota(@RequestBody Mascota mascota) {
        return service.addMascota(mascota);
    }

}
