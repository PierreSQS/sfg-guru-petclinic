package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OwnerController.class)
class OwnerControllerTest {

    public static final String PAGE_TITLE = "<title>PetClinic :: a Spring Framework demonstration</title>";

    @Autowired
    private MockMvc mockMvc;

    private Owner owner1;
    private Owner owner2;
    private List<Owner> owners;

    @MockBean
    private OwnerService ownerServiceMock;

    @BeforeEach
    void setUp() {
        owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Owner1");
        owner1.setLastName("Mock1");
        owner1.setPhone("1234567");

        owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Owner2");
        owner2.setLastName("Mock2");
        owner2.setPhone("1234567");

        owners = Arrays.asList(owner1, owner2);

    }

    @Test
    void initFindForm() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/findOwners"))
                .andExpect(content().string(containsString(PAGE_TITLE)));
        verifyNoInteractions(ownerServiceMock);
    }

    @Test
    void showOwner() throws Exception{
        // Given
        when(ownerServiceMock.findById(anyLong())).thenReturn(owner1);
        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(PAGE_TITLE)))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner",
                        hasProperty("firstName",is(owner1.getFirstName()))))
                .andDo(print());
    }

    @Test
    void processFindFormReturnManyOwners() throws Exception {
        // When
        when(ownerServiceMock.findAllByLastNameLike(anyString())).thenReturn(owners);

        // Then
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("selections"))
                .andExpect(view().name("owners/ownersList"))
                .andExpect(content().string(containsString("<h2>Owners</h2>")))
                .andDo(print());
    }

    @Test
    void processFindFormReturnOneOwner() throws Exception {
        // When
        when(ownerServiceMock.findAllByLastNameLike(anyString()))
                           .thenReturn(Collections.singletonList(owner2));

        // Then
        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + owner2.getId()))
                .andDo(print());
    }

    @Test
    void initCreateOwner() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(content().string(containsString("<h2>Owner</h2>")))
                .andDo(print());
    }

    @Test
    void processCreateOwner() throws Exception {
        MultiValueMap<String,String> ownerParamMap = new LinkedMultiValueMap<>();
        ownerParamMap.add("id",owner1.getId().toString());
        ownerParamMap.add("firstName",owner1.getFirstName());
        ownerParamMap.add("lastName",owner1.getLastName());

        when(ownerServiceMock.save(ArgumentMatchers.any(Owner.class))).thenReturn(owner1);

        mockMvc.perform(post("/owners/new")
                    .params(ownerParamMap))
                .andExpect(status().is3xxRedirection())
                .andDo(print());

        verify(ownerServiceMock).save(ArgumentMatchers.any(Owner.class));

    }

    @Test
    void initUpdateOwner() throws Exception {
        when(ownerServiceMock.findById(anyLong())).thenReturn(owner1);

        mockMvc.perform(get("/owners/{id}/edit",1))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(content().string(containsString("<h2>Owner</h2>")))
                .andDo(print());
    }


    @Test
    void processUpdateOwner() throws Exception {
        when(ownerServiceMock.save(ArgumentMatchers.any(Owner.class))).thenReturn(owner1);

        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("firstName","Joe");
        paramsMap.add("lastName", "Mock");

        mockMvc.perform(post("/owners/{id}/edit",owner1.getId())
                    .params(paramsMap))
                .andExpect(status().is3xxRedirection())
                .andDo(print());

        verify(ownerServiceMock).save(ArgumentMatchers.any(Owner.class));
    }
}