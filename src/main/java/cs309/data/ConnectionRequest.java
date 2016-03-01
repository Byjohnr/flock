package cs309.data;

import javax.persistence.*;

@Entity
@Table(name = "connection_request")
public class ConnectionRequest {

    @Id
    @Column
    public Integer id;

    @Column
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    public User userReceiving;

    @Column
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    public User userSending;



}
