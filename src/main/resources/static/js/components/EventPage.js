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
        console.log("event name change");
        this.setState({event:{eventName: event.target.value, eventDescription: this.state.event.eventDescription, location: this.state.event.location, id: this.state.event.id, creator: this.state.event.creator, eventStart: this.state.event.eventStart, eventEnd: this.state.event.eventEnd, type: this.state.event.type, commentList: this.state.event.commentList, eventInvites: this.state.event.eventInvites}});
    },
    handleDescriptionChange: function(event) {
        this.setState({event:{eventDescription: event.target.value, eventName: this.state.event.eventName, location: this.state.event.location, id: this.state.event.id, creator: this.state.event.creator, eventStart: this.state.event.eventStart, eventEnd: this.state.event.eventEnd, type: this.state.event.type, commentList: this.state.event.commentList, eventInvites: this.state.event.eventInvites}});
    },
    handleLocationChange: function(event) {
        this.setState({event:{location: event.target.value, eventDescription: this.state.event.eventDescription, eventName: this.state.event.eventName, id: this.state.event.id, creator: this.state.event.creator, eventStart: this.state.event.eventStart, eventEnd: this.state.event.eventEnd, type: this.state.event.type, commentList: this.state.event.commentList, eventInvites: this.state.event.eventInvites}});
    },
    onSubmit: function (){
        console.log("submitting");
        EventActions.editEvent(this.state.event);
    },
    createComment: function(){
        var comment = this.refs.commentString.value;
        EventActions.createComment(comment);
    },
    handleGoing: function() {
        EventActions.setAttending("Going");
    },
    handleMaybe: function() {
        EventActions.setAttending("Maybe");
    },
    handleNotGoing: function() {
        EventActions.setAttending("Not Going");
    },
    checkVisibilityGoing: function() {
        if (EventActions.getAttending() === 1) {
            return {visibility:'visible'};
        }
        else{
            return {visibility:'hidden'};
        }
    },


    render: function() {
        if (this.state.event === undefined) {
            return <div>Loading <i className="fa fa-spin fa-refresh"/></div>;
        }
        return (
            <div className="row">
                <NavBar/>
                <div className="col-sm-5 col-sm-offset-3">
                    <h1 className="text-center"> Event </h1>
                    <div>
                        <h3> {this.state.event.eventName} </h3>
                        <div className="btn-group" role="group">
                            <input type="button" className="btn btn-success" id="Going" onClick={this.handleGoing}>Going</input>
                            <button type="button" className="btn btn-primary" id="Maybe" onClick={this.handleMaybe}>Maybe</button>
                            <button type="button" className="btn btn-danger" id="Not Going" onClick={this.handleNotGoing}>Not Going</button>
                        </div>

                        <h3> {this.state.event.eventDescription} </h3>

                        <h3> Start: {this.state.event.eventStart} </h3>

                        <h3> End: {this.state.event.eventEnd} </h3>

                        <h3> Location: {this.state.event.location} </h3>
                    </div>
                    <button type="button" className="btn btn-primary btn-lg" data-toggle="modal"
                            data-target="#EditModal">
                        Edit
                    </button>
                    <div>
                        <h2 className="text-center"> Comments </h2>
                        <CommentList comments={this.state.event.commentList}/>
                        <form>
                            <div className="form-group">
                                <label htmlFor="createComment">Create Comment</label>
                                <input type="text" className="form-control" id="createComment" ref="commentString"/>
                            </div>
                        </form>
                        <input type="button" className="btn btn-default" value="Submit" onClick={this.createComment}/>
                    </div>
                    <div>
                        <h2 className="text-center"> Invited </h2>
                        <InviteList invites={this.state.event.eventInvites}/>
                    </div>
                    <div className="modal fade" id="EditModal" tabIndex="-1" role="dialog"
                         aria-labelledby="EditModalLabel">
                        <div className="modal-dialog" role="document">
                            <div className="modal-content">
                                <form onSubmit={this.onSubmit}>
                                    <div className="modal-header">
                                        <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span></button>
                                        <h4 className="modal-title" id="myModalLabel">Edit Event</h4>
                                    </div>
                                    <div className="modal-body">
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
                                            <input type="text" className="form-control" id="eventLocation" value={this.state.event.location}
                                                   onChange={this.handleLocationChange}/>
                                        </div>
                                    </div>
                                    <div className="modal-footer">
                                        <button type="button" className="btn btn-default" data-dismiss="modal">Close
                                        </button>
                                        <input type="submit" className="btn btn-primary" value="Save Changes"/>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
});
ReactDOM.render(<EventPage />, document.getElementById('event_div'));