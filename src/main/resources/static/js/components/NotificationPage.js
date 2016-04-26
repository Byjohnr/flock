var NotificationPage = React.createClass({
    render: function () {

        return (

            <div>
                <NavBar/>
                <div className="container">
                    <div className="row text-center">
                        <h1>Your Notifications</h1>
                    </div>
                    <div className="row">
                        <div className="col-md-12">
                            <NotificationList/>
                        </div>
                    </div>
                </div>
            </div>);
    }
});
ReactDOM.render(<NotificationPage />, document.getElementById("list_notification"));