var HomePage = React.createClass({
    render: function () {
        return (
            <div>
                <NavBar/>
                <div className="container">
                    <div className="row text-center">
                        <div className="col-md-6 col-xs-12">
                            <h1>Upcoming Events</h1>
                            <EventList/>
                        </div>
                        <div className="col-md-6 col-xs-12">
                            <h2>Recent Notifications</h2>
                            <NotificationList/>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
});

ReactDOM.render(<HomePage />, document.getElementById('event_div'));