package cs309.controller;

import cs309.data.*;
import cs309.dto.ConnectionDTO;
import cs309.dto.ConnectionGroupDTO;
import cs309.service.ConnectionService;
import cs309.service.NotificationService;
import cs309.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/connections/get")
    public List<ConnectionDTO> getConnections(Principal principal, @RequestParam(name = "eventId", required = false) Integer eventId, @RequestParam(name = "chatId", required = false) Integer chatId) {
        List<ConnectionDTO> connections = new ArrayList<>();
        if(eventId != null) {
            connectionService.getConnectionsNotInvited(principal.getName(), eventId).stream().forEach(user ->
                    connections.add(new ConnectionDTO(user.getId(), user.getFirstName(), user.getLastName())));
        } else if (chatId != null) {
            connectionService.getConnectionsNotInvitedToChat(principal.getName(), chatId).stream().forEach(user ->
                    connections.add(new ConnectionDTO(user.getId(), user.getFirstName(), user.getLastName())));
        } else {
            connectionService.getConnectionsByEmail(principal.getName()).stream().forEach(user ->
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
        connectionService.saveConnectionRequest(new ConnectionRequest(userSignedIn, otherUser));

        Notification notification = new Notification();
        notification.setReceiver(otherUser);
        notification.setCreator(userSignedIn);
        notification.setType(Notification.USER_CONNECTION);
        notification.setTypeId(userId);
        notificationService.saveNotification(notification);

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

        Notification notification = new Notification();
        notification.setReceiver(otherUser);
        notification.setCreator(userSignedIn);
        notification.setType(Notification.ACCEPT_USER_CONNECTION);
        notification.setTypeId(userId);
        notificationService.saveNotification(notification);

        return "connected";
    }

    @RequestMapping(value = "/connection/reject/{userId}", method = RequestMethod.POST)
    public String rejectConnectionRequest(@PathVariable int userId, Principal principal) {
        User userSignedIn = userService.getUserByEmail(principal.getName());
        User otherUser = userService.getUser(userId);
        connectionService.deleteConnectionRequest(userSignedIn, otherUser);
        return "nothing";
    }

    @RequestMapping(value = "/connectionGroups", method = RequestMethod.GET)
    public List<ConnectionGroup> connectionGroups(Principal principal) {
        return connectionService.getConnectionGroupByEmail(principal.getName());
    }

    @RequestMapping(value = "/connectionGroup/create", method = RequestMethod.POST)
    public void createConnectionGroup(@RequestBody String groupName, Principal principal) {
        User groupCreator = userService.getUserByEmail(principal.getName());
        ConnectionGroup connectionGroup = new ConnectionGroup(groupName, groupCreator);
        connectionService.saveConnectionGroup(connectionGroup);
    }

    @RequestMapping(value = "/connectionGroup/delete", method = RequestMethod.POST)
    public void deleteConnectionGroup(@RequestBody String groupId) {
        ConnectionGroup connectionGroup = connectionService.getConnectionGroupById(Integer.decode(groupId));
        connectionService.deleteConnectionGroup(connectionGroup);
    }

    @RequestMapping(value = "/connectionGroup/{groupId}/edit")
    @ResponseStatus(value = HttpStatus.OK)
    public void editConnectionGroupName(@RequestBody String groupName, @PathVariable int groupId) {
        ConnectionGroup connectionGroup = connectionService.getConnectionGroupById(groupId);
        if (groupName != null) {
            connectionGroup.setGroupName(groupName);
            connectionService.saveConnectionGroup(connectionGroup);
        }
    }

    @RequestMapping(value = "/connectionGroup/{groupId}/add", method = RequestMethod.POST)
    public void addConnectionToConnectionGroup(@RequestBody String userId, @PathVariable int groupId, Principal principal) {
        ConnectionGroupUser connectionGroupUser = new ConnectionGroupUser(userService.getUser(Integer.decode(userId)), connectionService.getConnectionGroupById(groupId));
        connectionService.saveConnectionGroupUser(connectionGroupUser);
    }

    @RequestMapping(value = "/connectionGroup/{groupId}/remove", method = RequestMethod.POST)
    public void removeConnectionFromConnectionGroup(@RequestBody String userId, @PathVariable int groupId, Principal principal) {
        ConnectionGroupUser connectionGroupUser = connectionService.getConnectionGroupUserByUserIdAndGroupId(Integer.decode(userId), groupId);
        connectionService.deleteConnectionGroupUser(connectionGroupUser);
    }

    @RequestMapping(value = "/connectionGroup/{groupId}")
    public ConnectionGroupDTO getConnectionGroupUsers(@PathVariable int groupId, Principal principal) {
        List<ConnectionDTO> connectionsInGroup = new ArrayList<>();
        List<ConnectionDTO> connectionsNotInGroup = new ArrayList<>();
        connectionService.getConnectionsInConnectionGroupByGroupId(groupId).stream().forEach(
                user -> connectionsInGroup.add(
                        new ConnectionDTO(user.getId(),user.getFirstName(),user.getLastName())
                )
        );
        connectionService.getConnectionsNotInGroupByGroupIdAndEmail(groupId, principal.getName())
                .stream().forEach(
                user -> connectionsNotInGroup.add(
                        new ConnectionDTO(user.getId(), user.getFirstName(), user.getLastName())
                )
        );
        ConnectionGroup connectionGroup = connectionService.getConnectionGroupById(groupId);

        return new ConnectionGroupDTO(connectionsInGroup, connectionsNotInGroup, (connectionGroup != null && !connectionGroup.getGroupName().isEmpty() ? connectionGroup.getGroupName() : "Group " + groupId));
    }
}