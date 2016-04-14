var NotificationPage = React.createClass({
        render: function() {

        return (

            <div>
                <NavBar/>
                <div><NotificationList/></div>
            </div>);
    }
});
ReactDOM.render(<NotificationPage />, document.getElementById("list_notification"));