package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
class OwnerControllerTest {

    MockMvc mockMvc;

    @Mock
    OwnerService ownerServiceMock;

    @InjectMocks
    OwnerController ownerController;

    @BeforeEach
    void setUp() {
        ownerController = new OwnerController(ownerServiceMock);
        mockMvc = standaloneSetup(ownerController).build();

        Owner owner1 = new Owner();
        owner1.setFirstName("owner1");
        Owner owner2 = new Owner();
        owner2.setFirstName("owner2");

        List<Owner> owners = Arrays.asList(owner1, owner2);
        when(ownerServiceMock.findAll()).thenReturn(owners);
    }

    @Test
    void listOwners() throws Exception {
        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owners"))
                .andExpect(model().attribute("owners",ownerServiceMock.findAll()))
                .andExpect(view().name("owners/index"))
                .andDo(print());
    }

    @Test
    void listOwnersByIndexPage() throws Exception {
        mockMvc.perform(get("/owners/index.html"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owners"))
                .andExpect(model().attribute("owners",hasSize(2)))
                .andExpect(header().string("Content-Language","en"))
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