
package com.br.schneiderstream.schneiderstream.controller.postagens.postagemLike;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.br.schneiderstream.schneiderstream.controller.postagens.PostagemRepository;
import com.br.schneiderstream.schneiderstream.controller.users.User;
import com.br.schneiderstream.schneiderstream.controller.users.UserDto;
import com.br.schneiderstream.schneiderstream.controller.users.UserRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/postagens/likes")
public class PostagemLikeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostagemLikeRepository repository;

    @Autowired
    private PostagemRepository postagemRepository;

    @GetMapping
    public PostagemLikeListDto getLikesFromPost(@RequestParam(name = "id") int postid) {
        List<PostagemLike> likes = repository.findAllByPostagemId(postid);
        int length = likes.size();
        List<UserDto> usersThatLikedList = new ArrayList<>(); // Inicializa a lista vazia

        likes.forEach(likeInfo -> {
            Optional<User> userOptional = userRepository.findById(likeInfo.getUserId());

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                UserDto userDto = new UserDto(user);
                usersThatLikedList.add(userDto);
            }
        });

        return new PostagemLikeListDto(usersThatLikedList, postid, length);
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
                    // throw new NotFoundException("Usuário já curtiu o post.");
                }
            } else {
                // throw new NotFoundException("ID de post inválido.");
            }
        } else {
            // throw new NotFoundException("ID de usuário inválido.");
        }
    }

    @Transactional
    @DeleteMapping
    public void removerLike(@RequestParam int userId, @RequestParam int postId) {

        boolean postExists = postagemRepository.existsById(postId);
        boolean userExists = userRepository.existsById(userId);
        boolean userLiked = repository.existsByPostagemIdAndUserId(postId, userId);

    }

}
