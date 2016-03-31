var EventList = React.createClass({
    mixins: [Reflux.connect(EventStore,'events')],
    getInitialState: function() {
        return {events : undefined};
    },
    componentDidMount: function() {
        console.log('componentMounted');
        EventActions.listEvents();
    },
    render: function() {
        console.log('rendering');
        if(this.state.events === undefined) {
            return <div>Loading <i className="fa fa-spin fa-refresh"/> </div>;
        }

            var eventNodes = this.state.events.map(function (event) {
                return (<EventLine key={event.eventId} data={event}/>);
            });
            if(eventNodes.length === 0) {
                return(<div className="text-center">No upcoming events. Sign up for some events!</div>)
            }
            return (
            <div>
                {eventNodes}
            </div>);
    }
});
