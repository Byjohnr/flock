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
        NotificationActions.fetchNotifications();
    },
    render: function() {
        console.log('rendering');
        if(this.state.notifications === undefined) {
            return <div> Loading <i className="fa fa-spin fa-refresh"/> </div>;
        }

        var notificationNodes = this.state.notifications.map(function (notification){
            return <tr>{notification.type}</tr>        });

        if(notificationNodes.length === 0) {
            return(<div className="text-center">You have no notifications!</div>)
        }
        return (
            <div>
                <table color="green" border="5" bgcolor="teal">
                    <tbody>
                            return <div NotificationList={"row"} key={i}>
                                {[ notificationNodes.name
                                ]}
                            </div>}
                            )})}
                        </tbody>
                    </table>
            </div>)
    }
});
ReactDOM.render(<NotificationList />, document.getElementById("list_notification"));