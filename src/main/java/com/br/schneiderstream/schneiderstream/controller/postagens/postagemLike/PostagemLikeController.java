
package com.br.schneiderstream.schneiderstream.controller.postagens.postagemLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.schneiderstream.schneiderstream.controller.postagens.PostagemRepository;
import com.br.schneiderstream.schneiderstream.controller.users.UserRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/postagens/likes")
public class PostagemLikeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostagemLikeRepository repository; // Certifique-se de importar o repositório adequado

    @Autowired
    private PostagemRepository postagemRepository;


    @GetMapping
    public int getLikesFromPost(@RequestParam(name = "id") int postid) {
        return repository.findAllByPostagemId(postid).size();
    }

    @PostMapping
    @Transactional
    public void darLike(@RequestBody PostagemLikeDto like) {

        boolean postExists = postagemRepository.existsById(like.postagemId());
        boolean userHaventLiked = !repository.existsByPostagemIdAndUserId(like.postagemId(), like.userId());
        boolean userExists = userRepository.existsById(like.userId());

        if (userExists) {
            if (postExists) {
                if (userHaventLiked) {
                    repository.save(new PostagemLike(like));
                } else {
                    throw new Error("Usuário já curtiu o post.");
                }
            } else {
                throw new Error("ID de post inválido.");
            }
        } else {
            throw new Error("ID de usuário inválido.");
        }
    }

}
