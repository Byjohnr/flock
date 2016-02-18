/**
 * Created by John on 2/11/2016.
 */

var NotificationList = React.createClass({
    mixins: [Reflux.connect(NotificationStore,'notifications')],
    getInitialState: function() {
        return {notifications : undefined};
    },
    componentDidMount: function() {
        console.log('componentMounted');
        notificationActions.fetchNotifications();
    },
    render: function() {
        console.log('rendering');
        if(this.state.notifications === undefined) {
            return <div> Loading <i className="fa fa-spin fa-refresh"/> </div>;
        }

        var notificationNodes = this.state.notifications.map(function (notification) {
            return (table.rows);
        });
        if(notificationNodes.length === 0) {
            return(<div className="text-center">You have no notifications!</div>)
        }
        return (
            <div>
                <table>
                    <TR>
                {notificationNodes}
                        </TR>
                    </table>
            </div>)
    }
});