var AdminEventsPage = React.createClass({
    mixins: [Reflux.connect(EventStore,'events')],
    getInitialState: function() {
        return {events : undefined};
    },
    componentDidMount: function() {
        EventActions.listAllEvents();
    },
    handleClick: function(event) {

    },
    render: function() {
        var eventNodes;
        if(this.state.events === undefined) {
            return <div>Loading <i className="fa fa-spin fa-refresh"/> </div>;
        }
        else {
            var parent = this;
            eventNodes = this.state.events.map(function (event) {
                var handleClick = parent.handleClick.bind(this, event);
                return (
                    <tr key={event.eventId}>
                        <td>{event.eventName}</td>
                        <td>{event.creator.firstName} {event.creator.lastName}</td>
                        <td>
                            <button id={event.eventId} type="button" className="btn btn-primary" onClick={handleClick}>
                                Delete
                            </button>
                        </td>
                    </tr>
                );
            });
        }
        return (
            <div>
                <table className="table" cols="3">
                    <tbody>
                    <tr>
                        <td> Event Name </td>
                        <td> Creator </td>
                        <td> Remove </td>
                    </tr>
                    {eventNodes}
                    </tbody>
                </table>
            </div>
        );
    }
});
ReactDOM.render(<AdminEventsPage />, document.getElementById('adminEvents_div'));