package io.juliocdet.unittesting.unittesting.service;

import io.juliocdet.unittesting.unittesting.model.Mascota;
import io.juliocdet.unittesting.unittesting.repository.MascotasDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MascotasService {

    private final MascotasDao mascotasDao;

    public ResponseEntity<?> getAllMascotas() {
        if (!mascotasDao.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(mascotasDao.findAll());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("non found");
        }
    }

    public ResponseEntity<?> addMascota(Mascota mascota) {
        Optional<Mascota> optionalMascota = mascotasDao.findById(mascota.getId());
        if (optionalMascota.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(optionalMascota.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("fail to add");
        }
    }
}
