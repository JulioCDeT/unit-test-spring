package io.juliocdet.unittesting.unittesting.repository;

import io.juliocdet.unittesting.unittesting.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotasDao extends JpaRepository<Mascota, Integer> {
}
