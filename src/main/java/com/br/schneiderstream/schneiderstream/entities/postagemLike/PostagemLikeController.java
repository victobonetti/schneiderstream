
package com.br.schneiderstream.schneiderstream.entities.postagemLike;

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

import com.br.schneiderstream.schneiderstream.entities.ActiveUserService;
import com.br.schneiderstream.schneiderstream.entities.postagens.repository.PostagemRepository;
import com.br.schneiderstream.schneiderstream.entities.users.classes.User;
import com.br.schneiderstream.schneiderstream.entities.users.dto.UserListDto;
import com.br.schneiderstream.schneiderstream.entities.users.repository.UserRepository;
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

    @Autowired
    private ActiveUserService userDataService;

    @GetMapping
    public PostagemLikeListDto getLikesFromPost(@RequestParam(name = "id") int postid) {
        List<PostagemLike> likes = repository.findAllByPostagemId(postid);
        int length = likes.size();
        List<UserListDto> usersThatLikedList = new ArrayList<>(); // Inicializa a lista vazia

        likes.forEach(likeInfo -> {
            Optional<User> userOptional = userRepository.findById(likeInfo.getUserId());

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                UserListDto userDto = new UserListDto(user);
                usersThatLikedList.add(userDto);
            }
        });

        return new PostagemLikeListDto(usersThatLikedList, postid, length);
    }

    @PostMapping
    @Transactional
    public void darLike(@RequestBody PostagemLikeCreateDto postId) {
        System.out.println("Like!");
        var userId = userDataService.getActiveUserData();
        
        boolean postExists = postagemRepository.existsById(postId.postagemId());
        boolean userHaventLiked = !repository.existsByPostagemIdAndUserId(postId.postagemId(), userId);
        boolean userExists = userRepository.existsById(userId);

        System.out.println(postId.postagemId());
        System.out.println(postExists);
        System.out.println(userHaventLiked);


        if (userExists && postExists && userHaventLiked) {
            System.out.println("Safe!");
            repository.save(new PostagemLike(postId, userId));
        }
    }

    @Transactional
    @DeleteMapping
    public void removerLike(@RequestParam int post) {

        var userId = userDataService.getActiveUserData();

        boolean postExists = postagemRepository.existsById(post);
        boolean userExists = userRepository.existsById(userId);
        boolean userLiked = repository.existsByPostagemIdAndUserId(post, userId);

        if (userExists && postExists && userLiked) {
            repository.deleteByUserIdAndPostagemId(userId, post);
        }

    }

}
