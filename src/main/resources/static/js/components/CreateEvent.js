var CreateEvent = React.createClass({
    mixins: [Reflux.connect(EventStore, 'errors')],
    getInitialState: function() {
        return {
            errors: undefined,
            invites : []
        }
    },
    onSubmit: function () {
        var formData = {
            eventName: this.refs.eventName.value,
            description: this.refs.description.value,
            startDate: this.refs.startDate.value + ' ' + this.refs.startTime.value,
            endDate: this.refs.endDate.value + ' ' + this.refs.endTime.value,
            type: this.refs.type.value,
            address: this.refs.address.value
        };
        EventActions.createEvent(formData, this.state.invites);
    },
    timePicker: function (id) {
        $('#' + id).pickatime();
    },
    datePicker: function (id) {
        $('#' + id).pickadate();
    },
    handleInvite : function(connection) {
        var newInvites = this.state.invites;
        newInvites.push(connection);
        console.log(newInvites);
        this.setState({invites : newInvites});
        //console.log(connection);
    },
    render: function () {
        console.log("Rendering create event");
        var invites = this.state.invites.map(function(connection) {
                return (" " + connection.firstName + " " + connection.lastName);
            });
        invites = invites.toString().substring(1);
        return (
            <div>
                <NavBar/>
                <h1 className="text-center">
                    Event Creation
                </h1>
                <form>
                    <div className="row">
                        <div className="col-sm-7">
                            <div className="form-horizontal">
                                <div className="form-group">
                                    <div>
                                        <label className="col-sm-2 control-label" htmlFor="name">Name of Event</label>
                                    </div>
                                    <div className="col-sm-8">
                                        <input id="name" className="form-control" type="text" ref="eventName"/>
                                    </div>
                                </div>
                                <div className="form-group">
                                    <div>
                                        <label className="col-sm-2 control-label" htmlFor="description">Event
                                            Description</label>
                                    </div>
                                    <div className="col-sm-8">
                                        <textarea id="description" className="form-control" rows="3" ref="description"/>
                                    </div>
                                </div>
                                <div className="form-group">
                                    <div>
                                        <label className="col-sm-2 control-label" htmlFor="startDate">Event Start
                                            Time</label>
                                    </div>
                                    <div className="col-sm-8">
                                        <div className="form-inline">
                                            <input id="startDate" className="form-control"
                                                   onClick={this.datePicker.bind(this,'startDate')} ref="startDate"/>
                                            <input id="startTime" className="form-control"
                                                   onClick={this.timePicker.bind(this,'startTime')} ref="startTime"/>
                                        </div>
                                    </div>
                                </div>
                                <div className="form-group">
                                    <div>
                                        <label className="col-sm-2 control-label" htmlFor="startDate">Event End
                                            Time</label>
                                    </div>
                                    <div className="col-sm-8">
                                        <div className="form-inline">
                                            <input id="endDate" className="form-control"
                                                   onClick={this.datePicker.bind(this,'endDate')} ref="endDate"/>
                                            <input id="endTime" className="form-control"
                                                   onClick={this.timePicker.bind(this,'endTime')} ref="endTime"/>
                                        </div>
                                    </div>
                                </div>
                                <div className="form-group">
                                    <div>
                                        <label className="col-sm-2 control-label" htmlFor="type">Event Type</label>

                                    </div>
                                    <div className="col-sm-8">
                                        <select id="type" className="form-control" ref="type">
                                            <option value="1">Open</option>
                                            <option value="2">Connections Only</option>
                                            <option value="3">Invite Only</option>
                                        </select>
                                    </div>
                                </div>
                                <ConnectionList handleInvite={this.handleInvite}/>
                                <div className="form-group">
                                    <div>
                                        <label className="col-sm-2 control-label" htmlFor="invites"> Invite List </label>
                                    </div>
                                    <div className="col-sm-8">
                                        <textarea id="invites" readOnly="readonly" className="form-control" rows="3" value={invites} />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="col-sm-5">
                            <div className="form-horizontal">
                                <div className="form-group">
                                    <div>
                                        <label className="col-sm-2 control-label" htmlFor="address">Address</label>
                                    </div>
                                    <div className="col-sm-8">
                                        <input id="address" className="form-control" rows="3" ref="address"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <input type="button" className="btn btn-default" value="Create Event" onClick={this.onSubmit}/>
                </form>
            </div>
        );
    }
});

ReactDOM.render(<CreateEvent />, document.getElementById('create_event'));