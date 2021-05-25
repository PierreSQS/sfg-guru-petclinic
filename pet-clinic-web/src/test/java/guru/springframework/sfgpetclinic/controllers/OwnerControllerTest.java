package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OwnerController.class)
@ExtendWith(SpringExtension.class)
class OwnerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    private List<Owner> owners;

    @MockBean
    private OwnerService ownerServiceMock;

    @BeforeEach
    void setUp() {
        Owner owner1 = new Owner();
        owner1.setFirstName("owner1");
        Owner owner2 = new Owner();
        owner2.setFirstName("owner2");

        owners = Arrays.asList(owner1, owner2);

    }

    @Test
    void listOwners() throws Exception {
        // Given
        when(ownerServiceMock.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owners"))
                .andExpect(model().attribute("owners",ownerServiceMock.findAll()))
                .andExpect(content().string(containsString("PetClinic :: a Spring Framework demonstration")))
                .andExpect(view().name("owners/index"))
                .andDo(print());
    }

    @Test
    void listOwnersByIndexPage() throws Exception {
        // Given
        when(ownerServiceMock.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/index.html"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owners"))
                .andExpect(model().attribute("owners",hasSize(2)))
                .andExpect(header().string("Content-Language","ene"))
                .andExpect(view().name("owners/index"))
                .andDo(print());
    }

    @Test
    void findOwner() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("owner"))
                .andExpect(view().name("notyetimplemented"));
        verifyZeroInteractions(ownerServiceMock);
    }
}