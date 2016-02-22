/**
 * Created by chasekoehler on 2/8/16.
 */
var EventPage = React.createClass({
    mixins: [Reflux.connect(EventStore,'event')],
    timePicker: function(id) {
        $('#' + id).pickatime();
    },
    datePicker: function(id) {
        $('#' + id).pickadate();
    },
    getInitialState: function() {
        return {event : undefined};
    },
    componentDidMount: function() {
        EventActions.getEvent();
    },
    handleNameChange: function(event) {
        this.setState({eventName: event.target.event.eventName});
    },
    handleDescriptionChange: function(event) {
        this.setState({eventDescription: event.target.event.eventDescription});
    },
    handleLocationChange: function(event) {
        this.setState({eventLocation: event.target.event.eventLocation});
    },
    onSubmit: function (){
        var formData = {
            eventName: this.refs.eventName.value,
            description: this.refs.description.value,
            location: this.refs.location.value
        };
        EventActions.editEvent(formData);
    },

    render: function() {
        if (this.state.event === undefined) {
            return <div>Loading <i className="fa fa-spin fa-refresh"/></div>;
        }
        return (
            <div className="row">
                <div className="col-sm-5 col-sm-offset-3">
                    <h1 className="text-center"> Event </h1>

                    <div>
                        <h3> {this.state.event.eventName} </h3>

                        <h3> {this.state.event.eventDescription} </h3>

                        <h3> Start: {this.state.event.eventStart} </h3>

                        <h3> End: {this.state.event.eventEnd} </h3>

                        <h3> Location: {this.state.event.eventLocation} </h3>
                    </div>
                    <button type="button" className="btn btn-primary btn-lg" data-toggle="modal"
                            data-target="#EditModal">
                        Edit
                    </button>
                    <div className="modal fade" id="EditModal" tabIndex="-1" role="dialog"
                         aria-labelledby="EditModalLabel">
                        <div className="modal-dialog" role="document">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span></button>
                                    <h4 className="modal-title" id="myModalLabel">Edit Event</h4>
                                </div>
                                <div className="modal-body">
                                    <form>
                                        <div className="form-group">
                                            <label htmlFor="eventName">Name of Event</label>
                                            <input type="text" className="form-control" id="eventName" value={this.state.event.eventName}
                                                   onChange={this.handleNameChange}/>
                                        </div>
                                        <div className="form-group">
                                            <label htmlFor="eventDescription">Event Description</label>
                                            <input type="text" className="form-control" id="eventDescription" value={this.state.event.eventDescription}
                                                   onChange={this.handleDescriptionChange}/>
                                        </div>
                                        <div className="form-group">
                                            <label htmlFor="eventStartDate">Start of the Event</label>
                                            <input id="startDate" className="form-control"
                                                   onClick={this.datePicker.bind(this,'startDate')}
                                                   ref="eventStartDate"/>
                                            <input id="startTime" className="form-control"
                                                   onClick={this.timePicker.bind(this,'startTime')}
                                                   ref="eventStartTime"/>
                                        </div>
                                        <div className="form-group">
                                            <label htmlFor="eventEndDate">End of the Event</label>
                                            <input id="startDate" className="form-control"
                                                   onClick={this.datePicker.bind(this,'startDate')} ref="eventEndDate"/>
                                            <input id="startTime" className="form-control"
                                                   onClick={this.timePicker.bind(this,'startTime')} ref="eventEndTime"/>
                                        </div>
                                        <div className="form-group">
                                            <label htmlFor="eventLocation">Event Location</label>
                                            <input type="text" className="form-control" id="eventLocation" value={this.state.event.eventLocation}
                                                   onChange={this.handleLocationChange}/>
                                        </div>
                                    </form>
                                </div>
                                <div className="modal-footer">
                                    <button type="button" className="btn btn-default" data-dismiss="modal">Close
                                    </button>
                                    <button type="button" className="btn btn-primary" onClick={this.onSubmit()}>Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
})
ReactDOM.render(<EventPage />, document.getElementById('event_div'));