/**
 * Created by jeffrey on 1/28/16.
 */
var EventList = React.createClass({
    mixins: [Reflux.connect(EventStore,'events')],
    //getInitialState: function() {
    //    return { events : []};
    //},
    //onListEvents: function(listEvents) {
    //    console.log('listEvents');
    //    this.setState({
    //        events : listEvents
    //    });
    //},
    //componentDidMount: function() {
    //    console.log('componentMounted');
    //    //this.listenTo(EventStore, this.onListEvents)
    //},
    render: function() {
        //EventActions.onListEvents();
        console.log('rendering');
        //console.log(this.state.events);
        if(this.state.events === undefined) {
            return <div>Loading <i className="a fa-refresh fa-spin"/> </div>;
        }

            var eventNodes = this.state.events.map(function (event) {
                return (<EventLine key={event.eventId} data={event}/>);
            });
        //console.log(eventNodes);
            if(eventNodes.length === 0) {
                return(<div className="text-center">No upcoming events. Sign up for some events!</div>)
            }
            return (
            <div>
                {eventNodes}
            </div>)
    }
});

//ReactDOM.render(<EventList />, document.getElementById('event_div'));