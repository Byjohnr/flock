/**
 * Created by jeffrey on 3/1/16.
 */
var ConnectionStatus = React.createClass({
    mixins: [Reflux.connect(ConnectionStore, 'status')],
    getInitialState : function() {
        return {status: undefined};
    },
    componentDidMount : function() {
        ConnectionActions.getConnectionStatus();
    },
    render: function() {

    }
});