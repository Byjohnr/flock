var ConnectionGroupPage = React.createClass({
    mixins: [Reflux.connect(ConnectionStore, 'groups')],
    getInitialState : function() {
        return {groups : undefined, groupName : undefined};
    },
    componentDidMount : function() {
        ConnectionActions.getConnectionGroups();
    },
    handleNameChange: function(groupName) {
      this.setState({groupName : groupName.target.value});
    },
    handleEditNameChange : function(groupId, groupName) {
        this.setState({groupName : groupName});
        this.setState({groupId : groupId});
    },
    handleNewGroup : function() {
        this.setState({groupName : undefined});
        this.setState({groupId : undefined});

    },
    handleDelete : function(groupId) {
        ConnectionActions.deleteConnectionGroup(groupId);
    },
    createGroup : function() {
        ConnectionActions.addConnectionGroup(this.state.groupName, this.state.groupId);
    },
    render: function() {
        var modalCreate = (
            <div id="createModal" className="modal fade" tabIndex="-1" role="dialog">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 className="modal-title">Connection Group</h4>
                        </div>
                        <div className="modal-body">
                            <div className="form-group">
                                    <label className="control-label" htmlFor="groupName">Name of Connection Group</label>
                                    <input id="groupName" className="form-control" type="text" value={this.state.groupName} onChange={this.handleNameChange}/>
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-primary" onClick={this.createGroup}>Save Group</button>
                        </div>
                    </div>
                </div>
            </div>
        );
        if(this.state.groups === undefined) {
            return (
                <div>Loading</div>
            );
        } else {
            var blah = this;
            var connectionGroups = this.state.groups.map(function(connectionGroup) {
                return (
                    <div key={connectionGroup.id} className="col-md-8 col-md-offset-1">
                        <div className="col-sm-8">
                            {connectionGroup.groupName}
                            <a href={'/account/connectionGroup/' + connectionGroup.id} className="btn btn-default">Manage Connections</a>
                            <button onClick={blah.handleEditNameChange.bind(blah, connectionGroup.id, connectionGroup.groupName)} className="btn btn-default"  data-toggle="modal" data-target="#createModal">Change Name</button>
                            <button className="btn btn-danger" onClick={blah.handleDelete.bind(blah, connectionGroup.id)}>Delete</button>
                        </div>
                    </div>
                );
            });
            return (
                <div className="row">
                    <NavBar />
                    {modalCreate}
                    <div className="col-md-8 col-md-offset-2">
                        <h2>Connection Groups
                            <span>
                                <button type="button" className="btn btn-primary btn-lg" data-toggle="modal" data-target="#createModal" onClick={this.handleNewGroup}>
                                    Create Connection Group
                                </button>
                            </span>
                        </h2>
                    </div>
                    {connectionGroups}
                </div>
            );
        }
    }
});
ReactDOM.render(<ConnectionGroupPage />, document.getElementById('connection_groups'));