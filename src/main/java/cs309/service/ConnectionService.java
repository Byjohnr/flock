package cs309.service;

import cs309.data.Connection;
import cs309.data.User;
import cs309.repo.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;

    public List<User> getConnections(String email) {
        return connectionRepository.getConnections(email);
    }

}
