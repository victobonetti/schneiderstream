package com.br.schneiderstream.schneiderstream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.br.schneiderstream.schneiderstream.controller.users.Title;
import com.br.schneiderstream.schneiderstream.controller.users.User;
import com.br.schneiderstream.schneiderstream.controller.users.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class SchneiderstreamApplicationTests {

	@Autowired
	private MockMvc m;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testGetUsers() throws Exception {
		MvcResult result = m.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andReturn();

		String jsonResponse = result.getResponse().getContentAsString();
		int arraySize = JsonPath.read(jsonResponse, "$.content.length()");

		if (arraySize >= 1) {
			m.perform(get("/users"))
					.andExpect(jsonPath("$.content[0].id").isNumber());
		}
	};

	@Test
	void testCreateUser() throws Exception {

		User user = new User();
		user.setCargo("Desbravador Ecológico");
		user.setDescricao("Amante da natureza e defensor do ambiente.");
		user.setEmail("ecoexplorer@example.com");
		user.setFoto("https://example.com/eco-explorer.jpg");
		user.setIdade(28);
		user.setNome("Eco Explorer");
		user.setScore(1500);
		user.setSenha("eco123");
		user.setTitle(Title.ESPECIALISTA_EM_ENERGIA_VERDE);

		MvcResult resultado = m.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk())
				.andReturn();

		m.perform(null);
		

	}

}
