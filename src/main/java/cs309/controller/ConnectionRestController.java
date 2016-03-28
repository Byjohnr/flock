package cs309.controller;

import cs309.data.Connection;
import cs309.data.ConnectionRequest;
import cs309.data.User;
import cs309.dto.ConnectionDTO;
import cs309.service.ConnectionService;
import cs309.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
//            Return 'requesting' if the current logged in user has already sent a connection request
            status = "requesting";
        } else if (connectionService.hasRequested(otherUser, userSignedIn)) {
            status = "requested";
        }
        return status;
    }

    @RequestMapping(value = "/connection/request/{userId}", method = RequestMethod.POST)
    public String requestConnection(@PathVariable int userId, Principal principal) {
        User userSignedIn = userService.getUserByEmail(principal.getName());
        User otherUser = userService.getUser(userId);
        connectionService.saveConnectionRequest(new ConnectionRequest(userSignedIn,otherUser));
        return "requesting";
    }

    @RequestMapping(value = "/connection/remove/{userId}", method = RequestMethod.POST)
    public String removeConnection(@PathVariable int userId, Principal principal) {
        User userSignedIn = userService.getUserByEmail(principal.getName());
        User otherUser = userService.getUser(userId);
        connectionService.deleteConnection(userSignedIn, otherUser);
        return "nothing";
    }

    @RequestMapping(value = "/connection/add/{userId}", method = RequestMethod.POST)
    public String addConnection(@PathVariable int userId, Principal principal) {
        User userSignedIn = userService.getUserByEmail(principal.getName());
        User otherUser = userService.getUser(userId);
        connectionService.saveConnection(new Connection(userSignedIn, otherUser));
        connectionService.deleteConnectionRequest(userSignedIn, otherUser);
        return "connected";
    }

    @RequestMapping(value = "/connection/reject/{userId}", method = RequestMethod.POST)
    public String rejectConnectionRequest(@PathVariable int userId, Principal principal) {
        User userSignedIn = userService.getUserByEmail(principal.getName());
        User otherUser = userService.getUser(userId);
        connectionService.deleteConnectionRequest(userSignedIn, otherUser);
        return "nothing";
    }
}
