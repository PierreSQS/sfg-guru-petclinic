package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {VisitController.class})
class VisitControllerTest {

    private Owner owner;
    private Pet pet;

    @MockBean
    VisitService visitSrvMock;

    @MockBean
    OwnerService ownerSrvMock;

    @MockBean
    PetService petSrvMock;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owner = new Owner();
        owner.setId(1L);
        owner.setFirstName("Owner1");
        owner.setLastName("Mock");

        pet = Pet.builder().id(1L).name("Fluppy").build();
        pet.setOwner(owner);
        pet.setPetType(PetType.builder().name("Dog").build());
        System.out.println(pet);

        when(ownerSrvMock.findById(anyLong())).thenReturn(owner);
        when(petSrvMock.findById(anyLong())).thenReturn(pet);
    }

    @Test
    void initCreateVisit() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/visits/new"
                            ,owner.getId(),pet.getId()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("pet",hasProperty("owner")))
                .andExpect(view().name("pets/createOrUpdateVisitForm"))
                .andExpect(content().string(containsString("Fluppy")))
                .andDo(print());
    }

    @Test
    void processCreateVisit() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/visits/new"
                ,owner.getId(),pet.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"))
                .andDo(print());
        verify(visitSrvMock).save(any(Visit.class));
    }

    @Test
    void processCreateVisitSpringTeamSolution() throws Exception {
        mockMvc.perform(post("/owners/*/pets/{petId}/visits/new", owner.getId())
                    .param("name", "George")
                    .param("description", "Visit Description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"))
                .andDo(print());
    }
}