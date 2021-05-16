package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = {OwnerController.class})
class OwnerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    OwnerService ownerServiceMock;

    @Test
    void listOwners() throws Exception {
        mockMvc.perform(get("/owners/index")).andDo(print());
    }

    @Test
    void findOwner() {
    }
}