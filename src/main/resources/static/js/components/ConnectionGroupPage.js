var ConnectionGroupPage = React.createClass({
    mixins: [Reflux.connect(ConnectionStore, 'connectionGroup')],
    getInitialState : function() {
        return {connectionGroup : undefined};
    },
    componentDidMount : function() {
        ConnectionActions.getConnectionGroup();
    },
    removeConnection: function(connection) {
        var updatedNotAddedList = this.state.connectionGroup.connectionsNotAdded;
        updatedNotAddedList.push(connection);
        var updatedAddedList = this.state.connectionGroup.connectionsAdded.filter(function(user) {
            return user.id != connection.id;
        });
        this.setState({connectionGroup : {connectionsAdded : updatedAddedList, connectionsNotAdded: updatedNotAddedList}});

        ConnectionActions
    },
    addConnection : function(connection) {
        var updatedAddedList = this.state.connectionGroup.connectionsAdded;
        updatedAddedList.push(connection);
        var updatedNotAddedList = this.state.connectionGroup.connectionsNotAdded.filter(function(user) {
           return user.id != connection.id;
        });
        this.setState({connectionGroup : {connectionsAdded : updatedAddedList, connectionsNotAdded: updatedNotAddedList}});
        console.log("added lol");
    },
    render: function() {
        if(this.state.connectionGroup === undefined) {
            return <div>Loading</div>
        } else {
            var addedUsers = this.state.connectionGroup.connectionsAdded.map(function(user) {
                var removeConnection = this.removeConnection.bind(this, user);
                return (
                    <tr key={user.id}>
                        <td>{user.firstName} {user.lastName}</td>
                        <td><button onClick={removeConnection}>Remove User</button></td>
                    </tr>

                );
            }, this);
            var notAddedUsers = this.state.connectionGroup.connectionsNotAdded.map(function(user) {
                var addConnection = this.addConnection.bind(this, user);
                return (
                    <tr key={user.id}>
                        <td>{user.firstName} {user.lastName}</td>
                        <td><button onClick={addConnection}>Add User</button></td>
                    </tr>
                );
            }, this);
            return (
                <div>
                    <NavBar />
                    <div>
                        <h2>Added Connections</h2>
                        <table>
                            <tbody>
                            <tr>
                                <td>User</td>
                                <td>Action</td>
                            </tr>
                            {addedUsers}
                            </tbody>
                        </table>
                    </div>
                    <div>
                        <h2>Unadded Connections</h2>
                        <table>
                            <tbody>
                            <tr>
                                <td>User</td>
                                <td>Action</td>
                            </tr>
                            {notAddedUsers}
                            </tbody>
                        </table>
                    </div>
                </div>
            );
        }
    }
});
ReactDOM.render(<ConnectionGroupPage />, document.getElementById("connection_group"));