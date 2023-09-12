package com.br.schneiderstream.schneiderstream.entities.postagemComentario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.schneiderstream.schneiderstream.entities.ActiveUserService;
import com.br.schneiderstream.schneiderstream.entities.Id;
import com.br.schneiderstream.schneiderstream.entities.users.classes.User;
import com.br.schneiderstream.schneiderstream.entities.users.dto.UserListDto;
import com.br.schneiderstream.schneiderstream.entities.users.repository.UserRepository;

@RestController
@RequestMapping("/postagens/comentarios")
public class PostagemComentarioController {

    @Autowired
    PostagemComentarioRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ActiveUserService userDataService;

    @GetMapping
    public List<PostagemComentarioListDto> encontrarComentariosDaPostagem(@RequestParam(name = "id") int postId) {
        List<PostagemComentario> comments = repository.findAllByPostagemId(postId).stream().toList();

        List<PostagemComentarioListDto> usersThatCommentedList = new ArrayList<>(); 

        comments.forEach(commentInfo -> {
            Optional<User> userOptional = userRepository.findById(commentInfo.getUserId());

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                usersThatCommentedList.add(new PostagemComentarioListDto(commentInfo, new UserListDto(user)));
            }
        });

        return usersThatCommentedList;

    }

    @PostMapping
    public Id criarComentario(@RequestBody PostagemComentarioDto comentario) {
        return new Id(repository.save(new PostagemComentario(comentario, userDataService.getActiveUserData())).getId());
    }

}
