package cs309.service;

import cs309.data.User;
import cs309.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jeffrey on 1/22/16.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;


    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public User getUser(int id) {
        return userRepo.findOne(id);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    public User getUserByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }
}
