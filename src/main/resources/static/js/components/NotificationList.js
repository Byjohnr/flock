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
            return <div> Loading <i className="fa fa-spin fa-refresh -align-center"/> </div>;
        }

        var notificationNodes = this.state.notifications.map(function (notification){
            return (<tr key={notification.id}>
                <td>{notification.type}</td>
                <td>{notification.message}</td>
                <td> <a className="btn btn-info active" href={notification.url} role="button">View</a>
                    <button type="button" className="btn btn-success">Accept</button>
                    <button type="button" className="btn btn-primary">Decline</button>
                     </td>
            </tr>);
        }, this);

        if(notificationNodes.length === 0) {
            return(<div className="text-center">You have no notifications!</div>)
        }
        //$.bootstrapSortable()   add this table later after backend works.
        return (
          <div>
                <NavBar/>
                <table className="table table-hover bg-info">
                    <tbody>
                    <tr>
                        <td data-firstsort="desc">
                            Type
                        </td>
                        <td>
                            Name
                        </td>
                    </tr>
                    {notificationNodes}
                    </tbody>
                </table>
          </div>)
    }
});
ReactDOM.render(<NotificationList />, document.getElementById("list_notification"));