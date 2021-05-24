package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private MockMvc mockMvc;

    private List<Owner> owners;

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