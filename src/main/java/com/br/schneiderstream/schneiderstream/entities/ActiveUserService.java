package com.br.schneiderstream.schneiderstream.entities;
import javax.management.RuntimeErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.schneiderstream.schneiderstream.entities.users.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class ActiveUserService {

    @Autowired
    UserRepository userRepository;

    private String getActiveUserDataEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return authentication.getName();
        } else {
            throw new RuntimeErrorException(null, "Problema ao resgatar token.");
        }
    }


    public int getActiveUserData() {
        String email = getActiveUserDataEmail();
        return userRepository.getIdByEmail(email);
    }


}
