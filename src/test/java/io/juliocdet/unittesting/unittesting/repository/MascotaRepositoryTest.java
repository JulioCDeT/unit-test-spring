package io.juliocdet.unittesting.unittesting.repository;


import io.juliocdet.unittesting.unittesting.model.Mascota;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
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
    private MascotasDao mascotasDao;

    @BeforeEach
    public void setup() {
        entityManager.clear();
    }

    @Test
    public void findById_getMascotaById_test() {
        // Given
        Mascota mascota = Mascota.builder()
                .nombre("Perro1")
                .edad(2)
                .tipo("perro")
                .build();
        entityManager.persist(mascota);
        int idToLookFor = (Integer) entityManager.getId(mascota);
        entityManager.flush();

        // When
        Optional<Mascota> optionalMascota = mascotasDao.findById(idToLookFor);

        // Them
        if (optionalMascota.isPresent()) {
            Assert.assertEquals(mascota, optionalMascota.get());
        } else {
            Assert.fail();
        }
    }

    @Test
    public void findById_notFound_test() {
        Assert.assertTrue(mascotasDao.findById(1999).isEmpty());
    }

    @Test
    public void delete_test() {
        Mascota mascota = Mascota.builder()
                .tipo("tipoChido")
                .edad(22)
                .nombre("shima")
                .build();

        entityManager.persist(mascota);
        int idToLookFor = (Integer) entityManager.getId(mascota);
        entityManager.flush();

        mascotasDao.deleteById(1);
        Optional<Mascota> mascotaOptional = mascotasDao.findById(idToLookFor);
        Assert.assertTrue(mascotaOptional.isEmpty());
    }

}
