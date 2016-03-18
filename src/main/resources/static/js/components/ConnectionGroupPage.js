var ConnectionGroupPage = React.createClass({
    mixins: [React.connect(ConnectionStore, 'connectionGroup')],
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
        //     TODO
        }
    }
});
ReactDOM.create(<ConnectionGroupPage />, document.getElementById("connection_group"))