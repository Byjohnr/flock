var ConnectionList = React.createClass({
   mixins: [Reflux.connect(ConnectionStore, 'connections')],
    getInitialState : function() {
        return [{connections : undefined}];
    },
    handleClick : function(connection) {
      console.log(connection);
        this.props.handleInvite(connection);
        $('#invite' + connection.id).prop("disabled", true);
        $('#invite' + connection.id).html("Added");

    },
    render: function() {
        var modalBody;
        if(this.state.connections === undefined) {
            modalBody = (<div>Loading...</div>);
            ConnectionActions.getConnections();
            console.log("undefined connections")
        } else {
            console.log("defined connections");
            modalBody = this.state.connections.map(function (connection) {
                var handleClick = this.handleClick.bind(this,connection);
                return (<tr key={connection.id}>
                    <td>{connection.firstName} {connection.lastName}</td>
                    <td><button id={'invite' + connection.id} type="button" className="btn btn-primary" onClick={handleClick}>Add to Invite List</button></td>
                </tr>);
            }, this);
            modalBody = <table className="table" cols="2">
                <tbody>
                <tr>
                    <td> Name </td>
                    <td> Invite </td>
                </tr>
                {modalBody}
                </tbody>
            </table>
        }
        return (
            <div>
                <button type="button" className="btn btn-primary" data-toggle="modal" data-target="#myModal">
                    Invite Connections
                </button>

                <div className="modal fade" id="myModal" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">
                            <div className="modal-header">
                                <button type="button" className="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 className="modal-title" id="myModalLabel">Connections</h4>
                            </div>
                            <div className="modal-body">
                                {modalBody}
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>)
    }
});