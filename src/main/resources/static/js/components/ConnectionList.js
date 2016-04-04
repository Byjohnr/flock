/**
 * Uses the following props:
 * actionId : helpful when there are more than one connection lists on the page, it is for handling the disabling of the buttons
 * actionName : The name of the button inside of the modal
 *
 * buttonName : The name of the visable button this places on the page
 * modalId : what id you want to give the id, make sure if you have more than 1 connection lists on the page you use different modalIds
 *
 * handleInvite : a function passed from the parent function to do something in the parent component
 */
var ConnectionList = React.createClass({
   mixins: [Reflux.connect(ConnectionStore, 'connections')],
    getInitialState : function() {
        return [{connections : undefined}];
    },
    handleClick : function(connection) {
      console.log(connection);
        this.props.handleInvite(connection);
        var idName = this.props.actionId;
        $('#' + idName + connection.id).prop("disabled", true);
        $('#' + idName + connection.id).html("Added");

    },
    render: function() {
        var modalBody;
        if(this.state.connections === undefined) {
            modalBody = (<div>Loading...</div>);
            ConnectionActions.getConnections();
        } else {
            var actionName = this.props.actionName;
            modalBody = this.state.connections.map(function (connection) {
                var handleClick = this.handleClick.bind(this,connection);
                return (<tr key={connection.id}>
                    <td>{connection.firstName} {connection.lastName}</td>
                    <td><button id={this.props.actionId + connection.id} type="button" className="btn btn-primary" onClick={handleClick}>{actionName}</button></td>
                </tr>);
            }, this);
            if (this.state.connections.length === 0) {
                console.log('no connections');
                modalBody = (
                    <tr>
                        <td>
                            No connections to invite
                        </td>
                        <td></td>
                    </tr>
                );
            }
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
                <button type="button" className="btn btn-primary" data-toggle="modal" data-target={'#' + this.props.modalId}>
                    {this.props.buttonName}
                </button>

                <div className="modal fade" id={this.props.modalId} tabIndex="-1" role="dialog" aria-labelledby="myModalLabel">
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