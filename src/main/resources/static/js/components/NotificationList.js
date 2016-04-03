var NotificationList = React.createClass({
    mixins: [Reflux.connect(NotificationStore, 'notifications')],
    getInitialState: function () {
        return {notifications: undefined};
    },
    componentDidMount: function () {
        console.log('componentMounted');
        NotificationActions.fetchNotifications();
    },
    handleDelete: function (id) {
        console.log('deleting');
        NotificationActions.deleteNotification(id);
    },

    render: function () {
        var notificationNodes;
        console.log('rendering');
        if (this.state.notifications === undefined) {
            return <div> Loading <i className="fa fa-spin fa-refresh align-center"/></div>;
        }
        var parent = this;
        notificationNodes = this.state.notifications.map(function (notification) {

            var handleClick = parent.handleDelete.bind(parent,notification.id);
            //handleClick=handleDelete(parent,id);

            return (<tr key={notification.id}>
                <td>{notification.type}</td>
                <td>{notification.message}</td>
                <td>
                    <a className="btn btn-info active btn-lg" href={notification.url} role="button">View</a>
                    <button id={notification.id} key={notification.id} type="button" className="btn btn-lg btn-danger" onClick={handleClick}> Delete
                    </button>
                </td>
                <td>

                </td>

            </tr>);
        });
        //<button type="button" className="btn btn-success">Accept</button>
        //<button type="button" className="btn btn-primary">Decline</button>


        if (notificationNodes.length === 0) {
            return (<div className="text-center">You have no notifications!</div>)
        }
        //$.bootstrapSortable()   add this table later after backend works.
        return (
            <div>
                <NavBar/>
                <div className="col-md-6 col-sm-offset-3">
                    <table className="table table-hover bg-info table-condensed">
                        <tbody>
                        <tr data-firstsort="desc" className="text-center">
                            Notifications
                        </tr>
                        {notificationNodes}
                        </tbody>
                    </table>
                </div>
            </div>);
    }
});
ReactDOM.render(<NotificationList />, document.getElementById("list_notification"));