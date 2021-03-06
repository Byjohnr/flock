package cs309.service;

import cs309.data.User;
import cs309.repo.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


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
        if(StringUtils.isBlank(query)) {
            return userRepo.findAll();
        }
        final String queryString = query.trim();
        String[] spacedQuery = queryString.split("\\s+");
        Specification<User> firstNameSpecification;
        Specification<User> lastNameSpecification = (root, query1, cb) -> cb.like(cb.lower(root.get("lastName")), queryString + "%");

        if (spacedQuery.length == 2) {
            firstNameSpecification = (root, query1, cb) -> cb.and(cb.like(cb.lower(root.get("firstName")), spacedQuery[0] + "%"), cb.like(cb.lower(root.get("lastName")), spacedQuery[1] + "%"));

        } else {
            firstNameSpecification = (root, query1, cb) -> cb.like(cb.lower(root.get("firstName")), queryString + "%");
        }
        List<User> users = userRepo.findAll(firstNameSpecification);
        users.addAll(userRepo.findAll(lastNameSpecification).stream().filter(user -> !users.contains(user)).collect(Collectors.toList()));
        int userSize = users.size();
        return userSize > 5 ? users.subList(0,5) : users;
    }
}
