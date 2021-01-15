package io.juliocdet.unittesting.unittesting.controller;

import io.juliocdet.unittesting.unittesting.model.Mascota;
import io.juliocdet.unittesting.unittesting.service.MascotasService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(MascotasController.class)
public class MascotaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MascotasService service;

    @Test
    public void getMascotas_whenGetMascotas_thenReturnJsonArrayMascotas()
            throws Exception {

        // Given
        Mascota perro = Mascota.builder()
                .nombre("perro")
                .tipo("canino")
                .edad(4)
                .build();

        Mascota gato = Mascota.builder()
                .nombre("gato")
                .tipo("felino")
                .edad(1)
                .build();

        Mascota pez = Mascota.builder()
                .nombre("pez")
                .tipo("marino")
                .edad(10)
                .build();
        List<Mascota> allMascotas = Arrays.asList(perro, gato, pez);

        ResponseEntity response = ResponseEntity
                .status(HttpStatus.OK)
                .body(allMascotas);

        Mockito.when(service.getAllMascotas()).thenReturn(response);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/mascotas");

        MvcResult result = mvc.perform(request).andReturn();
        String resultStrign = result.getResponse().getContentAsString();
        int statusResult = result.getResponse().getStatus();

        String expectedResult = "[{\"id\":0,\"nombre\":\"perro\",\"edad\":4,\"tipo\":\"canino\"},{\"id\":0,\"nombre\":\"gato\",\"edad\":1,\"tipo\":\"felino\"},{\"id\":0,\"nombre\":\"pez\",\"edad\":10,\"tipo\":\"marino\"}]";

        assertEquals(expectedResult, resultStrign);
        assertEquals(200, statusResult);
    }

    @Test
    public void getMascotas_emptyMascotasList() throws Exception {
        ResponseEntity response = ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("non found");

        Mockito.when(service.getAllMascotas()).thenReturn(response);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/mascotas");

        MvcResult result = mvc.perform(request).andReturn();
        String bodyResponse = result.getResponse().getContentAsString();
        int statusResponse = result.getResponse().getStatus();

        assertEquals("non found", bodyResponse);
        assertEquals(404, statusResponse);
    }
}
