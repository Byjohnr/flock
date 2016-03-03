package cs309.controller;

import cs309.dto.ConnectionDTO;
import cs309.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/connections/get")
    public List<ConnectionDTO> getConnections(Principal principal) {
        List<ConnectionDTO> connections = new ArrayList<>();
        if(principal != null) {
            connectionService.getConnections(principal.getName()).stream().forEach(user ->
                    connections.add(new ConnectionDTO(user.getId(), user.getFirstName(), user.getLastName())));
        }
        return connections;

    }
}
