package com.br.schneiderstream.schneiderstream.controller.postagens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.schneiderstream.schneiderstream.controller.Id;
import com.br.schneiderstream.schneiderstream.controller.postagens.postagemImagem.PostagemImagem;
import com.br.schneiderstream.schneiderstream.controller.postagens.postagemImagem.PostagemImagemDto;
import com.br.schneiderstream.schneiderstream.controller.postagens.postagemImagem.PostagemImagemRepository;
import com.br.schneiderstream.schneiderstream.controller.users.User;
import com.br.schneiderstream.schneiderstream.controller.users.UserListDto;
import com.br.schneiderstream.schneiderstream.controller.users.UserRepository;
import com.br.schneiderstream.schneiderstream.exceptions.BadRequestException;
import com.br.schneiderstream.schneiderstream.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private PostagemRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostagemImagemRepository imageRepository;

    @GetMapping
    public Page<PostagemListDto> listar(@PageableDefault Pageable p) {
        Page<PostagemListDto> page = repository.findAll(p).map(postagem -> {
            // encontrar imagem da postagem
            PostagemImagem imagem = imageRepository.findByPostId(postagem.getId()).orElse(null);
            // encontrar usuário autor da postagem
            User user = userRepository.findById(postagem.getUserId()).orElse(null);

            // conversão
            UserListDto userDto = new UserListDto(user);
            PostagemImagemDto imagemDto = (imagem == null) ? new PostagemImagemDto(null, null, null)
                    : new PostagemImagemDto(imagem);

            // retorna todos os dados juntos
            return new PostagemListDto(
                    postagem.getId(),
                    postagem.getTitulo(),
                    postagem.getConteudo(),
                    imagemDto,
                    userDto);
        });
        return page;
    }

    @PostMapping
    @Transactional
    public Id criar(@RequestBody @Valid PostagemCreateDto data) {

        int userId = data.postagem().userId();

        boolean userExists = userRepository.existsById(userId);
        if (!userExists) {
            throw new NotFoundException("ID de usuário inválido");
        }

        boolean imagemPropriedadesNotNull = data.imagem() != null;
        if (!imagemPropriedadesNotNull) {
            throw new BadRequestException("Atributo 'imagem' é nulo.");
        }

        boolean imagemAtributosValidos = (data.imagem().url() != null && data.imagem().alt() != null);
        if (!imagemAtributosValidos) {
            throw new BadRequestException("Dados do atributo 'imagem' inválidos.");

        }

        Postagem postagem = new Postagem(data.postagem());

        Postagem savedPostagem = repository.save(postagem); // Salva a postagem e obtém a instância salva com o ID

        PostagemImagem imagem = new PostagemImagem(data.imagem(), savedPostagem.getId());

        imageRepository.save(imagem);

        return new Id(savedPostagem.getId());
    }

    @GetMapping("/find")
    public Postagem encontrarPorId(@RequestParam int id) {
        Postagem postagem = repository.findById(id).orElse(null);
        if (postagem == null) {
            throw new NotFoundException("Id de postagem inválido.");
        }
        return postagem;
    }
}
