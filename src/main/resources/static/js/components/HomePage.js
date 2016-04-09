var HomePage = React.createClass({
 render: function() {
     return (
         <div className="row">
            <NavBar/>
             <div className="col-sm-5 col-sm-offset-3">
                 <h1 className="text-center">Upcoming Events</h1>
                 <EventList/>
             </div>
             <div className="col-md-8 col-md-4">
             <h2 className ="text-center">Recent Notifications</h2>
             <NotificationList/>
             </div>
         </div>
     );
 }
});

ReactDOM.render(<HomePage />, document.getElementById('event_div'));