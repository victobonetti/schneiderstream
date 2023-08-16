
package com.br.schneiderstream.schneiderstream.controller.postagemLike;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.schneiderstream.schneiderstream.controller.postagens.PostagemRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/postagens/likes")
public class PostagemLikeController {

    @Autowired
    private PostagemLikeRepository repository; // Certifique-se de importar o repositório adequado

    @Autowired
    private PostagemRepository postagemRepository;

    @GetMapping("/{postId}")
    public List<PostagemLike> getLikesFromPost(@RequestParam String postId) {
        System.out.println("Works!");

        int id = Integer.parseInt(postId);
        return repository.findAllByPostagemId(id);
    }

    @PostMapping
    @Transactional
    public void darLike(@RequestBody PostagemLikeDto like) {
        if (postagemRepository.existsById(like.postagemId())) {
            repository.save(new PostagemLike(like));
        } else {
            throw new Error("ID de post inválido");
        }
    }

}
