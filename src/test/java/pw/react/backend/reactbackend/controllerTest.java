package pw.react.backend.reactbackend;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pw.react.backend.reactbackend.controller.UserController;
import pw.react.backend.reactbackend.entity.User;

import pw.react.backend.reactbackend.repo.UserRepository;

import java.sql.Date;
import java.util.List;

@RunWith(SpringRunner.class)
public class controllerTest {

    @TestConfiguration
    static class controllerTestContextConfiguration {

        @Bean
        public UserController userController() {
            return new UserController();
        }
    }

    @Autowired
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User user1 = new User("Jack", "Smith","js", Date.valueOf("1997-11-30") ,true);
        user1.setId((long)1);
        userRepository.save(user1);
        User user2 = new User("Adam", "Johnson","aj", Date.valueOf("1996-10-29"),true);
        user2.setId((long)2);
        userRepository.save(user2);
        User user3 = new User("Kim", "Smith","ks", Date.valueOf("1995-9-28"),true);
        user3.setId((long)3);
        userRepository.save(user3);

        Mockito.when(userRepository.findOne(user1.getId()))
                .thenReturn(user1);
        Mockito.when(userRepository.findOne(user2.getId()))
                .thenReturn(user2);
        Mockito.when(userRepository.findOne(user3.getId()))
                .thenReturn(user3);
        Mockito.when(userRepository.findByLogin(user1.getLogin()))
                .thenReturn(user1);
        Mockito.when(userRepository.findByLogin(user2.getLogin()))
                .thenReturn(user2);
        Mockito.when(userRepository.findByLogin(user3.getLogin()))
                .thenReturn(user3);
    }


    @Test
    public void findByIdTest() {
        User test1 = userController.findById(1);
        Assertions.assertThat(test1.getId()).isEqualTo(1);

        User test2 = userController.findById(2);
        Assertions.assertThat(test2.getId()).isEqualTo(2);

        User test3 = userController.findById(3);
        Assertions.assertThat(test3.getId()).isEqualTo(3);
    }
    @Test
    public void findByLoginTest() {
        User test1 = userController.fetchDataByLogin("js");
        Assertions.assertThat(test1.getLogin()).isEqualTo("js");

        User test2 = userController.fetchDataByLogin("aj");
        Assertions.assertThat(test2.getLogin()).isEqualTo("aj");

        User test3 = userController.fetchDataByLogin("ks");
        Assertions.assertThat(test3.getLogin()).isEqualTo("ks");
    }
    @Test
    public void deleteTest() {
        userController.updateUser(1,new User("Clark", "Kent","ck", Date.valueOf("1975-3-3"),true));
        User test1 = userController.findById(1);
        Assertions.assertThat(test1.getFirstName()).isEqualTo("Clark");

    }
}
