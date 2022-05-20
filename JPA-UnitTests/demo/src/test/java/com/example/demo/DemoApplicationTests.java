package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource( locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = DemoApplication.class)
class DemoApplicationTests {

	@Autowired
	PetRepository repository;

	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void repositoryAddTest() {
		log.info("Testing repository add...");

		Pet pet = new Pet();
		pet.setName("el oga");
		pet.setAge(1);
		pet.setIp("100.100.100.100");
		pet.setSpecies("dog");
		pet.setTag("ABC-123");

		assertNull(pet.getId());

		this.repository.save(pet);
		log.info("New test pet id => {}", pet.getId());

		assertNotNull(pet.getId());
	}

	@Test
	public void mvcTest() throws UnsupportedEncodingException, Exception {
		Pet pet = new Pet();
		pet.setName("el oga");
		pet.setAge(1);
		pet.setIp("100.100.100.100");
		pet.setSpecies("dog");
		pet.setTag("ABC-123");

		String requestBody = objectMapper.writeValueAsString(pet);
		
		String result = mvc.perform(post("/pets").contentType("application/json").content(requestBody))
		                   .andExpect(status().isOk())
						   .andExpect(content().contentTypeCompatibleWith("application/json"))
						   .andDo(print())
						   .andExpect(jsonPath("$.name", is("el oga")))
				.andReturn().getResponse().getContentAsString();

		log.info(result);

	}
}
