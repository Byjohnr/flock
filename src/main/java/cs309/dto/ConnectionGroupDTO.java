package cs309.dto;

import java.util.List;

public class ConnectionGroupDTO {

    private List<ConnectionDTO> connectionsAdded;

    private List<ConnectionDTO> connectionsNotAdded;

    public ConnectionGroupDTO(List<ConnectionDTO> connectionsAdded, List<ConnectionDTO> connectionsNotAdded) {
        this.connectionsAdded = connectionsAdded;
        this.connectionsNotAdded = connectionsNotAdded;
    }

    public List<ConnectionDTO> getConnectionsAdded() {
        return connectionsAdded;
    }

    public void setConnectionsAdded(List<ConnectionDTO> connectionsAdded) {
        this.connectionsAdded = connectionsAdded;
    }

    public List<ConnectionDTO> getConnectionsNotAdded() {
        return connectionsNotAdded;
    }

    public void setConnectionsNotAdded(List<ConnectionDTO> connectionsNotAdded) {
        this.connectionsNotAdded = connectionsNotAdded;
    }
}
