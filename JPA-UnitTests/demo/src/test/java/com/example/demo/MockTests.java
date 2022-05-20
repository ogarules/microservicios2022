package com.example.demo;

import com.example.demo.controllers.PetController;
import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource( locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = DemoApplication.class)
public class MockTests {

    @MockBean
    PetRepository repository;

    @InjectMocks
    @Autowired
    PetController controller;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    public void mockRepositoryTest() throws UnsupportedEncodingException, Exception {
        log.info("TEsting mock repository insert...");
        Pet pet = new Pet();
        pet.setAge(5);
        pet.setIp("100.100.100.100");
        pet.setName("oga");
        pet.setSpecies("dog");
        pet.setTag("abc-123");

        given(repository.save(any())).willReturn(pet);

        String body = mapper.writeValueAsString(pet);

        String result = mvc.perform(post("/pets").contentType("application/json").content(body))
                           .andExpect(status().isOk())
                           .andExpect(content().contentTypeCompatibleWith("application/json"))
                           .andDo(print())
                           .andExpect(jsonPath("$.name", is("oga")))
                .andReturn().getResponse().getContentAsString();

        log.info(result);

        Mockito.verify(repository).save(any());
    }

    @Test
    public void mockRepositoryFindByIdTest() throws UnsupportedEncodingException, Exception {
        log.info("TEsting mock repository insert...");
        Pet pet = new Pet();
        pet.setAge(5);
        pet.setIp("100.100.100.100");
        pet.setName("oga");
        pet.setSpecies("dog");
        pet.setTag("abc-123");

        given(repository.findById(1L)).willReturn(Optional.of(pet));

        String body = mapper.writeValueAsString(pet);

        String result = mvc.perform(get("/pets/1").contentType("application/json").content(body))
                           .andExpect(status().isOk())
                           .andExpect(content().contentTypeCompatibleWith("application/json"))
                           .andDo(print())
                           .andExpect(jsonPath("$.name", is("oga")))
                .andReturn().getResponse().getContentAsString();

        log.info(result);

        Mockito.verify(repository).findById(any());
    }
}
