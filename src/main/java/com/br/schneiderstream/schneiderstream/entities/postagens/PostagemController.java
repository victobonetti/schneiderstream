package com.br.schneiderstream.schneiderstream.entities.postagens;

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

import com.br.schneiderstream.schneiderstream.entities.ActiveUserService;
import com.br.schneiderstream.schneiderstream.entities.Id;
import com.br.schneiderstream.schneiderstream.entities.postagemImagem.PostagemImagem;
import com.br.schneiderstream.schneiderstream.entities.postagemImagem.PostagemImagemDto;
import com.br.schneiderstream.schneiderstream.entities.postagemImagem.PostagemImagemRepository;
import com.br.schneiderstream.schneiderstream.entities.postagens.classes.Postagem;
import com.br.schneiderstream.schneiderstream.entities.postagens.dto.PostagemCreateDto;
import com.br.schneiderstream.schneiderstream.entities.postagens.dto.PostagemListDto;
import com.br.schneiderstream.schneiderstream.entities.postagens.repository.PostagemRepository;
import com.br.schneiderstream.schneiderstream.entities.users.classes.User;
import com.br.schneiderstream.schneiderstream.entities.users.dto.UserListDto;
import com.br.schneiderstream.schneiderstream.entities.users.repository.UserRepository;

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

    @Autowired
    private ActiveUserService userDataService;

    @GetMapping
    public Page<PostagemListDto> listar(@PageableDefault Pageable p) {
        System.out.println("Get posts...");
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

        int userId = userDataService.getActiveUserData();

        Postagem postagem = new Postagem(data.postagem(), userId);

        Postagem savedPostagem = repository.save(postagem); // Salva a postagem e obtém a instância salva com o ID

        PostagemImagem imagem = new PostagemImagem(data.imagem(), savedPostagem.getId());

        imageRepository.save(imagem);

        return new Id(savedPostagem.getId());
    }

    @GetMapping("/find")
    public Postagem encontrarPorId(@RequestParam int id) {
        Postagem postagem = repository.findById(id).orElse(null);
        return postagem;
    }
}
