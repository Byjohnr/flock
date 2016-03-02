package cs309.controller;

import cs309.data.User;
import cs309.dto.ConnectionDTO;
import cs309.service.ConnectionService;
import cs309.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ConnectionRestController {

    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private UserService userService;

    @RequestMapping("/connections/get")
    public List<ConnectionDTO> getConnections(Principal principal) {
        List<ConnectionDTO> connections = new ArrayList<>();
        if(principal != null) {
            connectionService.getConnections(principal.getName()).stream().forEach(user ->
                    connections.add(new ConnectionDTO(user.getId(), user.getFirstName(), user.getLastName())));
        }
        return connections;
    }

    @RequestMapping("/connection/status/{userId}")
    public String getConnectionStatus(@PathVariable int userId, Principal principal) {
        String status = "nothing";
        User userSignedIn = userService.getUserByEmail(principal.getName());
        User otherUser = userService.getUser(userId);
        if (connectionService.isAlreadyConnected(userSignedIn, otherUser)) {
            status = "connected";
        } else if (connectionService.hasRequested(userSignedIn, otherUser)) {
            status = "requesting";
        } else if (connectionService.hasBeenRequested(userSignedIn, otherUser)) {
            status = "requested";
        }
        return status;
    }
}
