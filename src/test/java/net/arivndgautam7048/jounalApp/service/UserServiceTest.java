package net.arivndgautam7048.jounalApp.service;

import net.arivndgautam7048.jounalApp.entity.User;
import net.arivndgautam7048.jounalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTest {

    @Autowired
   private UserRepository userRepository;

    @Autowired
   private UserService userService;

    @ParameterizedTest
    @Disabled
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
            assertTrue(userService.saveNewUser(user));

    }

    @Test
    @Disabled
    public void testCheckUserJournal(){

        User user = userRepository.findByUserName("arvind");
        assertTrue(user.getJournalEntries().isEmpty());

    }





    @Test
    @Disabled
    public void testAdd(){
        assertEquals(9,5+4,"failed for "+9);
    }
}
