package pw.react.backend.reactbackend.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pw.react.backend.reactbackend.entity.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByLogin(String login);
}
