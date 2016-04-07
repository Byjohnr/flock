var AdminEventsPage = React.createClass({
    mixins: [Reflux.connect(EventStore,'events')],
    getInitialState: function() {
        return {events : undefined,
            eventId : undefined
        };
    },
    componentDidMount: function() {
        EventActions.listAllEvents();
        this.setState({eventId : 0})
    },
    handleDelete: function() {
        EventActions.deleteEvent(this.state.eventId);
        $('#ConformationModal').modal('hide');
    },
    handleEventId: function(id) {
        this.setState({eventId : id});
        $('#ConformationModal').modal('show');
    },
    render: function() {
        var eventNodes;
        if(this.state.events === undefined) {
            return <div>Loading <i className="fa fa-spin fa-refresh"/> </div>;
        }
        else {
            var parent = this;
            eventNodes = this.state.events.map(function (event) {
                var handleClick = parent.handleEventId.bind(parent,event.id);
                return (
                    <tr key={event.id}>
                        <td><a href={'/event/'+ event.id}> {event.eventName}</a></td>
                        <td><a href={'/user/'+ event.creator.id} role>{event.creator.firstName} {event.creator.lastName}</a></td>
                        <td>
                            <button id={event.id} key={event.id} type="button" className="btn btn-danger" onClick={handleClick}>
                                Delete
                            </button>
                        </td>
                    </tr>
                );
            });
        }
        return (
            <div>
                <NavBar/>
                <table className="table" cols="3">
                    <tbody>
                    <tr >
                        <td> Event Name </td>
                        <td> Creator </td>
                        <td> Remove </td>
                    </tr>
                    {eventNodes}
                    </tbody>
                </table>
                <div className="modal fade" id="ConformationModal" tabIndex="-1" role="dialog"
                     aria-labelledby="ConformationModalLabel">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h4 className="modal-title" id="myModalLabel">Are You Sure You Would Like to Delete?</h4>
                            </div>
                            <div className="modal-body">
                                <div className="btn-group" role="group">
                                    <button type="button" className="btn btn-primary" onClick={this.handleDelete}>
                                        Yes
                                    </button>
                                    <button type="button" className="btn btn-danger" data-dismiss="modal">
                                        No
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
});
ReactDOM.render(<AdminEventsPage />, document.getElementById('adminEvents_div'));