package pw.react.backend.reactbackend.service;


import org.springframework.http.ResponseEntity;
import pw.react.backend.reactbackend.entity.User;

public interface UserService {
    User checkUserByLogin(String login);
}
