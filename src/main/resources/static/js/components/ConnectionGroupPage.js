var ConnectionGroupPage = React.createClass({
    mixins: [Reflux.connect(ConnectionStore, 'connectionGroup')],
    getInitialState : function() {
        return {connectionGroup : undefined};
    },
    componentDidMount : function() {
        ConnectionActions.getConnectionGroup();
    },
    render: function() {
        if(this.state.connectionGroup === undefined) {
            return <div>Loading</div>
        } else {
            return (
                <div>
                    <div>
                        <h2>Added Connections</h2>
                    </div>
                    <div>
                        <h2>Unadded Connections</h2>
                    </div>
                </div>
            );
        }
    }
});
ReactDOM.create(<ConnectionGroupPage />, document.getElementById("connection_group"));