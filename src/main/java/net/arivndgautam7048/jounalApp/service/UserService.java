package net.arivndgautam7048.jounalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.arivndgautam7048.jounalApp.entity.User;
import net.arivndgautam7048.jounalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository ;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    private static final Logger logger = LoggerFactory.getLogger(UserService.class);// only one instance for this class


    public boolean saveNewUser(User user){

      try {
          user.setPassword(passwordEncoder.encode(user.getPassword())); // hash only on registration
          user.setRoles(Arrays.asList("USER"));
          userRepository.save(user);
          return true;
      }catch (Exception e){
          log.warn("Already present ",e);
          return false;
      }

    }
    public void saveNewAdmin(User user) {
        if (user.getUserName() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Username and password must not be null");
        }

        // Check if username already exists
        if (userRepository.findByUserName(user.getUserName()) != null) {
            throw new RuntimeException("Username already exists: " + user.getUserName());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }



    public void saveUser(User user){
        userRepository.save(user); // no password re-encoding
    }


    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
