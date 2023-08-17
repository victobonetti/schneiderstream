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
import com.br.schneiderstream.schneiderstream.controller.users.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private PostagemRepository repository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Page<PostagemListDto> listar(@PageableDefault Pageable p) {
        System.out.println("Works!");  
        Page<PostagemListDto> page = repository.findAll(p).map(PostagemListDto::new);
        return page;
    }

    @PostMapping
    @Transactional
    public void criar(@RequestBody @Valid PostagemDto postagem) {

        int userId = Integer.parseInt(postagem.userId());
        boolean userExists = userRepository.existsById(userId);

        if (userExists) {
            System.out.println(userExists);
            repository.save(new Postagem(postagem));
        } else {
            throw new Error("ID de usuário inválido");
        }
    }

}
