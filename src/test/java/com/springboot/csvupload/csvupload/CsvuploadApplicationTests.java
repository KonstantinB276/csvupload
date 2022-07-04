package com.springboot.csvupload.csvupload;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.csvupload.csvupload.entity.Person;
import com.springboot.csvupload.csvupload.repositories.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CsvuploadApplicationTests {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	PersonRepository personRepository;

	@AfterEach
	public void resetDb() {
		personRepository.deleteAll();
	}

	private Person createTestPerson(String name) {
		Person person = new Person();
		person.setIdColor(1);
		person.setName(name);
		person.setLastname("Max");
		person.setAddress("12345 Maxstadt");
		return personRepository.save(person);
	}

	@Test
	public void testFindAllPersons() throws Exception {
		Person p1 = createTestPerson("Musterman");
		Person p2 = createTestPerson( "Musterman1");
		mockMvc.perform(get("/persons"))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(p1, p2))));
	}

	@Test
	public void testFindPersonById() throws Exception {
		int id = createTestPerson("Musterman").getId();
		mockMvc.perform(get("/persons/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.name").value("Musterman"));
	}

	@Test
	public void testFindPersonByColor() throws Exception {
		Person person = createTestPerson("Musterman");
		String color = person.getColor();
		mockMvc.perform(get("/persons/color/{color}", color))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(person))));
	}

}
