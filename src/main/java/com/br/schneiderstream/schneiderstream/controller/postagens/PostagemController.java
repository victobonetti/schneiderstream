package com.br.schneiderstream.schneiderstream.controller.postagens;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.schneiderstream.schneiderstream.controller.postagens.postagemImagem.PostagemImagem;
import com.br.schneiderstream.schneiderstream.controller.postagens.postagemImagem.PostagemImagemDto;
import com.br.schneiderstream.schneiderstream.controller.postagens.postagemImagem.PostagemImagemRepository;
import com.br.schneiderstream.schneiderstream.controller.users.User;
import com.br.schneiderstream.schneiderstream.controller.users.UserListDto;
import com.br.schneiderstream.schneiderstream.controller.users.UserRepository;
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
            PostagemImagem imagem = imageRepository.findByPostId(postagem.getUserId()).orElse(null);
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
    public void criar(@RequestBody @Valid PostagemCreateDto data) {

        int userId = data.postagem().userId();
        boolean userExists = userRepository.existsById(userId);

        if (userExists) {
            Postagem postagem = new Postagem(data.postagem());
            Postagem savedPostagem = repository.save(postagem); // Salva a postagem e obtém a instância salva com o ID

            if (data.imagem() != null) {
                PostagemImagem imagem = new PostagemImagem(data.imagem(), savedPostagem.getId());
                imageRepository.save(imagem);
            }
        } else {
            throw new NotFoundException("ID de usuário inválido");
        }

    }

}
