package pl.arek.petclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.arek.petclinic.model.Owner;
import pl.arek.petclinic.services.OwnerService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

//    @Test
//    void listOwners() throws Exception {
//        when(ownerService.findAll()).thenReturn(owners);
//        mockMvc.perform(get("/owners"))
//                .andExpect(status().is(200))
//                .andExpect(view().name("owners/index"))
//                .andExpect(model().attribute("owners", hasSize(2)));
//        //.idOk()
//    }

//    @Test
//    void listOwnersByIndex() throws Exception {
//        when(ownerService.findAll()).thenReturn(owners);
//        mockMvc.perform(get("/owners/index"))
//                .andExpect(status().is(200))
//                .andExpect(view().name("owners/index"))
//                .andExpect(model().attribute("owners", hasSize(2)));
//        //.idOk()
//    }

//    @Test
//    void findOwners() throws Exception {
//        mockMvc.perform(get("/owners/find"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("notImplemented"));
//
//        verifyZeroInteractions(ownerService);
//    }

    @Test
    void processFindFormReturnMany() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString()))
                .thenReturn(Arrays.asList(Owner.builder().id(1L).build(), Owner.builder().id(2L).build()));

        mockMvc.perform((get("/owners")))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).build()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void displayOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/123"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }


}