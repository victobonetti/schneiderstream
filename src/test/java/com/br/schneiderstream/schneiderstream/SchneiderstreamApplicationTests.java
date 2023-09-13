package com.br.schneiderstream.schneiderstream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.br.schneiderstream.schneiderstream.entities.Id;
import com.br.schneiderstream.schneiderstream.entities.auth.AuthDto;
import com.br.schneiderstream.schneiderstream.entities.postagemImagem.PostagemImagem;
import com.br.schneiderstream.schneiderstream.entities.postagemLike.PostagemLikeDto;
import com.br.schneiderstream.schneiderstream.entities.postagens.classes.Postagem;
import com.br.schneiderstream.schneiderstream.entities.postagens.dto.PostagemCreateDto;
import com.br.schneiderstream.schneiderstream.entities.users.classes.Title;
import com.br.schneiderstream.schneiderstream.entities.users.classes.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class SchneiderstreamApplicationTests {

	@Autowired
	private MockMvc m;

	@Autowired
	private ObjectMapper objectMapper;

	public int deserializeId(MvcResult result)
			throws UnsupportedEncodingException, JsonMappingException, JsonProcessingException {
		String createdUserId = result.getResponse().getContentAsString();
		Id idClass = objectMapper.readValue(createdUserId, Id.class);
		return idClass.id();
	}

	public User returnTestUser() {
		User user = new User();
		user.setCargo("Desbravador Ecológico");
		user.setDescricao("Amante da natureza e defensor do ambiente.");
		user.setEmail("lixa3sexplorer@example.com");
		user.setFoto("https://example.com/eco-explorer.jpg");
		user.setIdade(28);
		user.setNome("Eco Explorer");
		user.setScore(1500);
		user.setSenha("eco123");
		user.setTitle(Title.ESPECIALISTA_EM_ENERGIA_VERDE);
		return user;
	}

	public PostagemCreateDto returnTestPostagemDto(int id) {

		// create postInfo
		Postagem postagemInfo = new Postagem();
		postagemInfo.setConteudo("lorem ipsum");
		postagemInfo.setTitulo("lorem");
		postagemInfo.setUserId(id);

		// create postImage
		PostagemImagem postagemImagem = new PostagemImagem();
		postagemImagem.setAlt("Imagem teste");
		postagemImagem.setUrl("url.teste.com");

		// create post
		PostagemCreateDto postagem = new PostagemCreateDto(postagemInfo, postagemImagem);
		return postagem;
	}

	public MvcResult criaUsuario(User user) throws JsonProcessingException, Exception {
		MvcResult resultCreate = m.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)))
				.andExpect(status().isOk())
				.andReturn();
		return resultCreate;
	}

	public MvcResult criaPostagem(PostagemCreateDto postagem) throws JsonProcessingException, Exception {
		MvcResult response = m.perform(post("/postagens").content(objectMapper.writeValueAsString(postagem))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
		return response;
	}

	public int criaUsuarioERetornaId() throws JsonProcessingException, Exception {
		User user = returnTestUser();
		MvcResult resultado = criaUsuario(user);
		return deserializeId(resultado);
	}

	public int criaPostagemERetornaId(int userId) throws JsonProcessingException, Exception {
		PostagemCreateDto postagem = returnTestPostagemDto(userId);
		MvcResult resultado = criaPostagem(postagem);
		return deserializeId(resultado);
	}

	public String getAuthenticationToken() throws JsonProcessingException, Exception {

		return m.perform(post("/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper
				.writeValueAsString(new AuthDto(returnTestUser().getEmail(), returnTestUser().getSenha()))))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.token").isNotEmpty())
				.andReturn()
				.getResponse()
				.getContentAsString();

	}

	@Test
	void testCreateUser() throws Exception {

		int id = criaUsuarioERetornaId();

		m.perform(get("/users/find")

				.param("id", String.valueOf(id)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(id));
	}

	@Test
	void testGetUsers() throws Exception {
		MvcResult result = m.perform(get("/users").header("Authorization", "Bearer " + getAuthenticationToken()))
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
	void testCreatePost() throws Exception {
		int userId = criaUsuarioERetornaId();
		int postId = criaPostagemERetornaId(userId);
		m.perform(get("/postagens/find").header("Authorization", "Bearer " + getAuthenticationToken()).param("id", String.valueOf(postId)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(postId));
	}

	@Test
	void testCreateLikesIntoPost() throws Exception {
		int user1Id = criaUsuarioERetornaId();
		int user2Id = criaUsuarioERetornaId();
		int postId = criaPostagemERetornaId(user1Id);

		PostagemLikeDto like = new PostagemLikeDto(user2Id, postId);

		m.perform(post("/postagens/likes")
		.header("Authorization", "Bearer " + getAuthenticationToken())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(like)))
				.andExpect(status().isOk());

		m.perform(get("/postagens/likes").header("Authorization", "Bearer " + getAuthenticationToken()).param("id", String.valueOf(postId)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalLikes")
						.value(1));
	}

	@Test
	void testCreateDuplicateLike() throws Exception {
		int user1Id = criaUsuarioERetornaId();
		int user2Id = criaUsuarioERetornaId();
		int postId = criaPostagemERetornaId(user1Id);

		PostagemLikeDto like = new PostagemLikeDto(user2Id, postId);

		// Primeira criação do like
		m.perform(post("/postagens/likes").header("Authorization", "Bearer " + getAuthenticationToken())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(like)))
				.andExpect(status().isOk());

		// Tentativa de criar o like novamente para o mesmo usuário e postagem
		m.perform(post("/postagens/likes").header("Authorization", "Bearer " + getAuthenticationToken())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(like)))
				.andExpect(status().isNotFound());
	}

	@Test
	void testRemoveLike() throws Exception {
		int user1Id = criaUsuarioERetornaId();
		int postId = criaPostagemERetornaId(user1Id);

		// Criação do like
		PostagemLikeDto like = new PostagemLikeDto(user1Id, postId);
		m.perform(post("/postagens/likes").header("Authorization", "Bearer " + getAuthenticationToken())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(like)))
				.andExpect(status().isOk());

		// Verificação do total de likes após a remoção
		m.perform(get("/postagens/likes").header("Authorization", "Bearer " + getAuthenticationToken()).param("id", String.valueOf(postId)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalLikes").value(1));

		// Remoção do like
		m.perform(delete("/postagens/likes")
		.header("Authorization", "Bearer " + getAuthenticationToken())
				.param("userId", String.valueOf(user1Id))
				.param("postId", String.valueOf(postId)))
				.andExpect(status().isOk());

		// Verificação do total de likes após a remoção
		m.perform(get("/postagens/likes").header("Authorization", "Bearer " + getAuthenticationToken()).param("id", String.valueOf(postId)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalLikes").value(0));
	}
}
