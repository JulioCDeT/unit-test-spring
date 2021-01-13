package io.juliocdet.unittesting.unittesting.service;

import io.juliocdet.unittesting.unittesting.model.Mascota;
import io.juliocdet.unittesting.unittesting.repository.MascotasDao;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class MascotaServiceTest {

    @Mock
    private MascotasDao repository;

    @InjectMocks
    private MascotasService service;

    @Test
    public void getAllMascotas_positiveTest() {


        Mascota mascota = Mascota.builder()
                .nombre("perro1")
                .id(1)
                .edad(2)
                .tipo("canino")
                .build();
        List<Mascota> mascotas = new ArrayList<>();
        mascotas.add(mascota);

        Mockito.when(repository.findAll()).thenReturn(Collections.singletonList(
                        new Mascota(1, "perro1", 2, "canino")
        ));

        Assert.assertThat(service.getAllMascotas().getBody(), equalTo(mascotas));
    }

}
