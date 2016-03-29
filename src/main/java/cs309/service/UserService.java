package cs309.service;

import cs309.data.User;
import cs309.data.specifications.UserSpecification;
import cs309.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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

    public List<User> getUsersByCurrentCity(String currentCity) {
        return userRepo.findByCurrentCity(currentCity);
    }

    public User getUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Transactional
    public void saveUser(User user) {
        userRepo.save(user);
    }

    public List<User> userSearch(String query) {
        Specification<User> specification =  new UserSpecification(query);
        return userRepo.findAll(specification);
    }
}
