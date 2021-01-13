package io.juliocdet.unittesting.unittesting.repository;


import io.juliocdet.unittesting.unittesting.model.Mascota;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MascotaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MascotasDao employeeRepository;

    @Test
    public void save_test() {
        // Given
        Mascota mascota = Mascota.builder()
                .nombre("Perro1")
                .edad(2)
                .tipo("perro")
                .build();
        entityManager.persist(mascota);
        entityManager.flush();

        // When
        Optional<Mascota> optionalMascota = employeeRepository.findById(1);

        // Them
        if (optionalMascota.isPresent()) {
            Assert.assertEquals(mascota, optionalMascota.get());
        } else {
            Assert.fail();
        }

    }

}
