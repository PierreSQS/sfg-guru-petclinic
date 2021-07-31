package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
@ExtendWith(SpringExtension.class)
class PetControllerTest {

    private Pet pet1;
    private Pet pet2;

    private Owner owner1;

    @MockBean
    PetService petSrvMock;

    @MockBean
    PetTypeService petTypeSrvMock;

    @MockBean
    OwnerService ownerSrvMock;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owner1 = new Owner();
        owner1.setFirstName("Owner1");
        owner1.setLastName("Mock1");
        pet1 = Pet.builder().id(1L).name("Fluppy").build();
        pet1.setOwner(owner1);

        pet2 = Pet.builder().id(2L).name("Tessy").build();
    }

    @Test
    void testInitPetUpdateForm() throws Exception {

        when(petSrvMock.findById(anyLong())).thenReturn(pet1);
        mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/edit", 1, 1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pet"))
                .andExpect(content().string(containsString("value=\"Fluppy")))
                .andDo(print());
    }

    @Test
    void processPetUpdateForm() throws Exception {
        // Given
        when(ownerSrvMock.findById(anyLong())).thenReturn(owner1);

        mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit",1, 1))
                .andExpect(status().is3xxRedirection())
                .andDo(print());

        verify(petSrvMock).save(ArgumentMatchers.any(Pet.class));
    }
}