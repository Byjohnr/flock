var ConnectionList = React.createClass({
   mixins: [Reflux.connect(ConnectionStore, 'connections')],
    getInitialState : function() {
        return {connections : undefined};
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
                return (<tr key={connection.id}>
                    <td><input type="checkbox"/></td>
                    <td>{connection.firstName} {connection.lastName}</td>
                </tr>);
            });
            modalBody = <table className="table" cols="2">
                <tbody>
                <tr>
                    <td> Invite </td>
                    <td> Name </td>
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
                                <h4 className="modal-title" id="myModalLabel">Modal title</h4>
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