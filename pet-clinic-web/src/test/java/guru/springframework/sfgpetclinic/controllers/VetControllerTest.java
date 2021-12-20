package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {VetController.class})
class VetControllerTest {

    private Vet v1;

    private Vet v2;

    @MockBean
    VetService vetSrvMock;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        Speciality radiology = new Speciality();
        radiology.setId(1L);
        radiology.setDescription("Radiology");

        Speciality dentistry = new Speciality();
        dentistry.setId(2L);
        dentistry.setDescription("Dentistry");

        Set<Speciality> specialities = Set.of(dentistry,radiology);

        v1 = new Vet();
        v1.setId(1L);
        v1.setFirstName("Stefan");
        v1.setLastName("Jockenhövel");
        v1.setSpecialities(specialities);

        v2 = new Vet();
        v2.setId(2L);
        v2.setFirstName("Adamou");
        v2.setLastName("Ndam Njoya");

        List<Vet> vets = List.of(v1, v2);

        // given
        given(vetSrvMock.findAll()).willReturn(vets);
    }

    @Test
    void listVets() throws Exception {
        // when, then
        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists())
                .andExpect(model().attribute("vets",is(not(empty()))))
                .andExpect(model().attribute("vets",containsInAnyOrder(v1, v2)))
                .andExpect(view().name("vets/index"))
                .andDo(print());
    }

    @Test
    void getVetsAsJson() throws Exception {
        mockMvc.perform(get("/api/v1/vets"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[1].lastName").value("Ndam Njoya"))
// Order of Specialities not guaranteed
//                .andExpect(jsonPath("$[0].specialities[1].description")
//                        .value(is(equalTo("Radiology"))))
                .andDo(print());
    }
}