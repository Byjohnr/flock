package cs309.dto;

import java.util.List;

public class ConnectionGroupDTO {

    private List<ConnectionDTO> connectionsAdded;
    private List<ConnectionDTO> connectionsNotAdded;
    private String groupName;

    public ConnectionGroupDTO(List<ConnectionDTO> connectionsAdded, List<ConnectionDTO> connectionsNotAdded, String groupName) {
        this.connectionsAdded = connectionsAdded;
        this.connectionsNotAdded = connectionsNotAdded;
        this.groupName = groupName;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
