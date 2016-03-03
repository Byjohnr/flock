var ConnectionStatus = React.createClass({
    mixins: [Reflux.connect(ConnectionStore, 'status')],
    getInitialState : function() {
        return {status: undefined};
    },
    componentDidMount : function() {
        ConnectionActions.getConnectionStatus();
    },
    handleRequest: function() {
        ConnectionActions.requestConnection();
    },
    handleRemove: function() {
        ConnectionActions.removeConnection();
    },
    handleAdd: function() {
        ConnectionActions.addConnection();
    },
    render: function() {
        if (this.state.status === undefined) {
            return <div>Wait</div>
        } else if (this.state.status == "nothing") {
            return (
            <div>
                <button className="btn btn-success" onClick={this.handleRequest}>Request Connection</button>
            </div>
            );
        } else if (this.state.status == "connected") {
            return (
                <div>
                    <button className="btn btn-danger" onClick={this.handleRemove}>Remove Connection</button>
                </div>
            );
        } else if (this.state.status == "requesting") {
            return (
                <div>
                    <button className="btn btn-success" disabled="disabled">Request Sent</button>
                </div>
            );
        } else if (this.state.status == "requested") {
            return (
                <div>
                    <button className="btn btn-success" onClick={this.handleAdd}>Accept Connection Request</button>
                </div>
            );
        } else {
            return (
                <div>
                    YOLOSWAG
                </div>
            );
        }
    }
});