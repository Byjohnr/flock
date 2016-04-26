var EventPage = React.createClass({
    mixins: [Reflux.connect(EventStore, 'event'), Reflux.connect(EventInviteStore, 'eventInvite'), Reflux.connect(RoleStore, 'role')],
    timePicker: function (id) {
        var this_input = $('#' + id).pickatime();
        var this_timepicker = this_input.pickatime('picker');
        this_timepicker.set('interval', 5);
        var parent = this;
        if (id === 'startTime') {
            this_timepicker.on('set', function () {
                var start_date;
                if (parent.state.event != undefined && parent.state.event.eventStart != null) {
                    start_date = new Date(parent.state.event.eventStart);
                    start_date.setHours(this_timepicker.get('select').hour);
                    start_date.setMinutes(this_timepicker.get('select').mins);
                    start_date = start_date.getTime();
                } else {
                    start_date = new Date();
                    start_date.setHours(this_timepicker.get('select').hour);
                    start_date.setMinutes(this_timepicker.get('select').mins);
                    start_date = start_date.getTime();
                }
                var start_string = parent.state.event.eventStartString.split('|');
                start_string[1] = this_timepicker.get('select', 'h:i A');
                parent.setState({
                    event: {
                        eventStart: start_date,
                        eventStartString: (start_string[0].length > 0 ? start_string[0] + ' | ' : '') + start_string[1],
                        eventEnd: parent.state.event.eventEnd,
                        eventEndString: parent.state.event.eventEndString,
                        eventName: parent.state.event.eventDescription,
                        eventDescription: parent.state.event.eventDescription,
                        location: parent.state.event.location,
                        id: parent.state.event.id,
                        creator: parent.state.event.creator,
                        type: parent.state.event.type,
                        commentList: parent.state.event.commentList,
                        eventInvites: parent.state.event.eventInvites
                    }
                });
            })
        } else if (id === 'endTime') {
            this_timepicker.on('set', function () {
                var end_date;
                if (parent.state.event != undefined && parent.state.event.eventEnd != null) {
                    end_date = new Date(parent.state.event.eventEnd);
                    end_date.setHours(this_timepicker.get('select').hour);
                    end_date.setMinutes(this_timepicker.get('select').mins);
                    end_date = end_date.getTime();
                } else {
                    end_date = new Date();
                    end_date.setHours(this_timepicker.get('select').hour);
                    end_date.setMinutes(this_timepicker.get('select').mins);
                    end_date = end_date.getTime();
                }
                var end_string = parent.state.event.eventEndString.split('|');
                end_string[1] = this_timepicker.get('select', 'h:i A');
                parent.setState({
                    event: {
                        eventEnd: end_date,
                        eventEndString: (end_string[0].length > 0 ? end_string[0] + ' | ' : '') + end_string[1],
                        eventStart: parent.state.event.eventStart,
                        eventStartString: parent.state.event.eventStartString,
                        eventName: parent.state.event.eventDescription,
                        eventDescription: parent.state.event.eventDescription,
                        location: parent.state.event.location,
                        id: parent.state.event.id,
                        creator: parent.state.event.creator,
                        type: parent.state.event.type,
                        commentList: parent.state.event.commentList,
                        eventInvites: parent.state.event.eventInvites
                    }
                });
            })
        }
    },
    datePicker: function (id) {
        var this_input = $('#' + id).pickadate();
        var this_datepicker = this_input.pickadate('picker');
        var parent = this;
        if (id === 'startDate') {
            this_datepicker.on('set', function () {
                var start_date;
                if (parent.state.event != undefined && parent.state.event.eventStart != null) {
                    start_date = new Date(parent.state.event.eventStart);
                    var start_hour = start_date.getHours();
                    var start_mins = start_date.getMinutes();
                    start_date = new Date(this_datepicker.get('select').pick);
                    start_date.setHours(start_hour);
                    start_date.setMinutes(start_mins);
                    start_date = start_date.getTime();
                } else {
                    start_date = this_datepicker.get('select').pick;
                }
                var start_string = parent.state.event.eventStartString.split('|');
                start_string[0] = this_datepicker.get('select', 'mmmm dd');
                parent.setState({
                    event: {
                        eventStart: start_date,
                        eventStartString: start_string[0] + (start_string[1] != undefined ? ' | ' + start_string[1] : ''),
                        eventEnd: parent.state.event.eventEnd,
                        eventEndString: parent.state.event.eventEndString,
                        eventName: parent.state.event.eventDescription,
                        eventDescription: parent.state.event.eventDescription,
                        location: parent.state.event.location,
                        id: parent.state.event.id,
                        creator: parent.state.event.creator,
                        type: parent.state.event.type,
                        commentList: parent.state.event.commentList,
                        eventInvites: parent.state.event.eventInvites
                    }
                });
            })
        } else if (id === 'endDate') {
            this_datepicker.on('set', function () {
                var end_date;
                if (parent.state.event != undefined && parent.state.event.eventEnd != null) {
                    end_date = new Date(parent.state.event.eventEnd);
                    var end_hour = end_date.getHours();
                    var end_mins = end_date.getMinutes();
                    end_date = new Date(this_datepicker.get('select').pick);
                    end_date.setHours(end_hour);
                    end_date.setMinutes(end_mins);
                    end_date = end_date.getTime();
                } else {
                    end_date = this_datepicker.get('select').pick;
                }
                var end_string = parent.state.event.eventEndString.split('|');
                end_string[0] = this_datepicker.get('select', 'mmmm dd');
                parent.setState({
                    event: {
                        eventEnd: end_date,
                        eventEndString: end_string[0] + (end_string[1] != undefined ? ' | ' + end_string[1] : ''),
                        eventStart: parent.state.event.eventStart,
                        eventStartString: parent.state.event.eventStartString,
                        eventName: parent.state.event.eventDescription,
                        eventDescription: parent.state.event.eventDescription,
                        location: parent.state.event.location,
                        id: parent.state.event.id,
                        creator: parent.state.event.creator,
                        type: parent.state.event.type,
                        commentList: parent.state.event.commentList,
                        eventInvites: parent.state.event.eventInvites
                    }
                });
            });
        }
    },
    getInitialState: function () {
        return {
            event: undefined,
            eventInvite: undefined,
            role: undefined,
            invites: [],
            eventAdmins: []
        };
    },
    componentDidMount: function () {
        EventActions.getEvent();
        EventInviteActions.getAttending();
        RoleActions.isEventAdmin();
    },
    handleNameChange: function (event) {
        this.setState({
            event: {
                eventName: event.target.value,
                eventDescription: this.state.event.eventDescription,
                location: this.state.event.location,
                id: this.state.event.id,
                creator: this.state.event.creator,
                eventStart: this.state.event.eventStart,
                eventStartString: this.state.event.eventStartString,
                eventEnd: this.state.event.eventEnd,
                eventEndString: this.state.event.eventEndString,
                type: this.state.event.type,
                commentList: this.state.event.commentList,
                eventInvites: this.state.event.eventInvites
            }
        });
    },
    handleDescriptionChange: function (event) {
        this.setState({
            event: {
                eventDescription: event.target.value,
                eventName: this.state.event.eventName,
                location: this.state.event.location,
                id: this.state.event.id,
                creator: this.state.event.creator,
                eventStart: this.state.event.eventStart,
                eventStartString: this.state.event.eventStartString,
                eventEnd: this.state.event.eventEnd,
                eventEndString: this.state.event.eventEndString,
                type: this.state.event.type,
                commentList: this.state.event.commentList,
                eventInvites: this.state.event.eventInvites
            }
        });
    },
    handleLocationChange: function (event) {
        this.setState({
            event: {
                location: event.target.value,
                eventDescription: this.state.event.eventDescription,
                eventName: this.state.event.eventName,
                id: this.state.event.id,
                creator: this.state.event.creator,
                eventStart: this.state.event.eventStart,
                eventStartString: this.state.event.eventStartString,
                eventEnd: this.state.event.eventEnd,
                eventEndString: this.state.event.eventEndString,
                type: this.state.event.type,
                commentList: this.state.event.commentList,
                eventInvites: this.state.event.eventInvites
            }
        });
    },
    onSubmit: function () {
        this.setState({
            event: {
                eventEnd: new Date(this.state.event.eventEnd),
                eventEndString: this.state.event.eventEndString,
                eventStart: new Date(this.state.event.eventStart),
                eventStartString: this.state.event.eventStartString,
                eventName: this.state.event.eventDescription,
                eventDescription: this.state.event.eventDescription,
                location: this.state.event.location,
                id: this.state.event.id,
                creator: this.state.event.creator,
                type: this.state.event.type,
                commentList: this.state.event.commentList,
                eventInvites: this.state.event.eventInvites
            }
        });
        EventActions.editEvent(this.state.event);
        $('#EditModal').modal('hide');
    },
    createComment: function () {
        var comment = this.refs.commentString.value;
        EventActions.createComment(comment);
    },
    handleGoing: function () {
        EventInviteActions.setAttending("Going");
        this.onSubmit();
    },
    handleMaybe: function () {
        EventInviteActions.setAttending("Maybe");
        this.onSubmit();
    },
    handleNotGoing: function () {
        EventInviteActions.setAttending("Not Going");
        this.onSubmit();
    },
    handleInviteChange: function () {
        EventInviteActions.setAttending("Change");
    },
    handleSendInvite: function (connection) {
        var newInvites = this.state.invites;
        newInvites.push(connection);
        this.setState({invites: newInvites});
        EventActions.editInvites(this.state.invites, this.state.eventAdmins);
        this.setState({invites: []});

    },
    handleEventAdmin: function (connection) {
        var newEventAdmins = this.state.eventAdmins;
        newEventAdmins.push(connection);
        this.setState({eventAdmins: newEventAdmins});
        EventActions.editInvites(this.state.invites, this.state.eventAdmins);
        EventActions.editEventAdmins(this.state.eventAdmins);
        this.setState({eventAdmins: []});
    },
    handleJoin: function () {
        EventActions.joinEvent();
        this.setState({eventInvite: 1})
    },

    render: function () {
        if (this.state.event === undefined) {
            return <div>Loading <i className="fa fa-spin fa-refresh"/></div>;
        }
        var attending;
        var edit;
        var invite;
        var picture;
        var map = (<Map data={this.state.event} height='300px' width='300px'/>);
        if (this.state.eventInvite == "0") {
            attending = (
                <div className="btn-group" role="group">
                    <button type="button" className="btn btn-success" id="Going" onClick={this.handleGoing}>Going
                    </button>
                    <button type="button" className="btn btn-primary" id="Maybe" onClick={this.handleMaybe}>Maybe
                    </button>
                    <button type="button" className="btn btn-danger" id="Not Going" onClick={this.handleNotGoing}>Not
                        Going
                    </button>
                </div>);
        }
        if (this.state.eventInvite == "1") {
            attending = (
                <div className="btn-group" role="group">
                    <button type="button" className="btn btn-success" id="Going">Going</button>
                    <button type="button" className="btn btn-link" onClick={this.handleInviteChange}>Change</button>
                </div>);
        }
        if (this.state.eventInvite == "2") {
            attending = (
                <div className="btn-group" role="group">
                    <button type="button" className="btn btn-primary" id="Maybe">Maybe</button>
                    <button type="button" className="btn btn-link" onClick={this.handleInviteChange}>Change</button>
                </div>
            )
        }
        if (this.state.eventInvite == "3") {
            attending = (
                <div className="btn-group" role="group">
                    <button type="button" className="btn btn-danger" id="Not Going">Not Going</button>
                    <button type="button" className="btn btn-link" onClick={this.handleInviteChange}>Change</button>
                </div>
            )
        }
        if (this.state.eventInvite == "4") {
            attending = (
                <button type="button" className="btn btn-primary" id="Join" onClick={this.handleJoin}>Join</button>
            )
        }
        if (this.state.role.toString() === "true") {
            edit = (
                <div className="btn-group" role="group">
                    <button type="button" className="btn btn-primary btn-lg" data-toggle="modal"
                            data-target="#EditModal">
                        Edit
                    </button>
                </div>
            );
            invite = (
                <div>
                    <div id="eventAdmin">
                        <ConnectionList actionId="eventAdmins" modalId="eventAdminModal"
                                        handleInvite={this.handleEventAdmin} buttonName="Add Event Admins"
                                        actionName="Add as Event Admin" type="event"/>
                    </div>
                    <div id="invites">
                        <ConnectionList actionId="inviteList" modalId="inviteModal" handleInvite={this.handleSendInvite}
                                        buttonName="Invite Connections" actionName="Add to Invite List" type="event"/>
                    </div>
                </div>
            );
            picture = (<Picture
                sendingUrl={"/api/picture_upload/event_picture/" + this.state.event.id}
                pictureEditable={true}
                getterUrl={"/api/event_picture/" + this.state.event.id}/>);
        }
        else {
            picture = (<Picture
                sendingUrl={""}
                pictureEditable={false}
                getterUrl={"/api/event_picture/" + this.state.event.id}/>);
        }

        return (
            <div className="row">
                <NavBar/>
                <div className="col-sm-5 col-sm-offset-3">
                    <h1 className="text-center"> {this.state.event.eventName} </h1>
                    <div>
                        {picture}
                    </div>
                    <div>
                        {map}
                    </div>
                    <div>
                        <div>
                            {attending}
                        </div>

                        <h3> {this.state.event.eventDescription} </h3>

                        <h3> Start: {this.state.event.eventStartString} </h3>

                        <h3> End: {this.state.event.eventEndString} </h3>

                        <h3> Location: {this.state.event.location} </h3>
                    </div>
                    {edit}
                    <div>
                        <h2> Comments </h2>
                        <CommentList comments={this.state.event.commentList}/>

                        <form>
                            <div className="form-group">
                                <label htmlFor="createComment">Create Comment</label>
                                <input type="text" className="form-control" id="createComment" ref="commentString"/>
                            </div>
                        </form>
                        <input type="button" className="btn btn-default" value="Submit"
                               onClick={this.createComment}/>
                    </div>
                    <div>
                        <h2 className="text-center"> Invited </h2>
                        <InviteList invites={this.state.event.eventInvites}/>
                    </div>
                    <div>
                        {invite}
                    </div>
                    <div className="modal fade" id="EditModal" tabIndex="-1" role="dialog"
                         aria-labelledby="EditModalLabel">
                        <div className="modal-dialog" role="document">
                            <div className="modal-content">
                                <form>
                                    <div className="modal-header">
                                        <button type="button" className="close" data-dismiss="modal"
                                                aria-label="Close">
                                            <span aria-hidden="true">&times;</span></button>
                                        <h4 className="modal-title" id="myModalLabel">Edit Event</h4>
                                    </div>
                                    <div className="modal-body">
                                        <div className="form-group">
                                            <label htmlFor="eventName">Name of Event</label>
                                            <input type="text" className="form-control" id="eventName"
                                                   value={this.state.event.eventName}
                                                   onChange={this.handleNameChange}/>
                                        </div>
                                        <div className="form-group">
                                            <label htmlFor="eventDescription">Event Description</label>
                                            <input type="text" className="form-control" id="eventDescription"
                                                   value={this.state.event.eventDescription}
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
                                            <input id="endDate" className="form-control"
                                                   onClick={this.datePicker.bind(this,'endDate')}
                                                   ref="eventEndDate"/>
                                            <input id="endTime" className="form-control"
                                                   onClick={this.timePicker.bind(this,'endTime')}
                                                   ref="eventEndTime"/>
                                        </div>
                                        <div className="form-group">
                                            <label htmlFor="eventLocation">Event Location</label>
                                            <input type="text" className="form-control" id="eventLocation"
                                                   value={this.state.event.location}
                                                   onChange={this.handleLocationChange}/>
                                        </div>
                                    </div>
                                    <div className="modal-footer">
                                        <button type="button" className="btn btn-default" data-dismiss="modal">Close
                                        </button>
                                        <button type="button" className="btn btn-primary" onClick={this.onSubmit}>Save
                                            Changes
                                        </button>
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