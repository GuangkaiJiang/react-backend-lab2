package pw.react.backend.reactbackend.service;
import org.hibernate.loader.plan.spi.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pw.react.backend.reactbackend.entity.User;
import pw.react.backend.reactbackend.repo.UserRepository;


import java.util.Optional;

@Service
public
class UserServiceImpl implements UserService {

    UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public User checkUserByLogin(String login) {
        User existingUser = repository.findByLogin(login);
        return existingUser;
    }
}
