var CreateEvent = React.createClass({
    mixins: [Reflux.connect(EventStore, 'errors')],
    getInitialState: function() {
        return {
            errors: undefined,
            invites : [],
            eventAdmins: [],
            latitude: undefined,
            longitude: undefined
        }
    },
    onSubmit: function (latitude, longitude) {
        console.log(this.state.longitude);
        console.log(this.refs.tagList.tagId.value);
        var formData = {
            eventName: this.refs.eventName.value,
            description: this.refs.description.value,
            startDate: this.refs.startDate.value + ' ' + this.refs.startTime.value,
            endDate: this.refs.endDate.value + ' ' + this.refs.endTime.value,
            type: this.refs.type.value,
            address: this.refs.address.value,
            tagId: this.refs.tagList.tagId.value,
            longitude: longitude,
            latitude: latitude
        };
        EventActions.createEvent(formData, this.state.invites, this.state.eventAdmins);
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
    handleEventAdmin : function(connection) {
        var newEventAdmins = this.state.eventAdmins;
        newEventAdmins.push(connection);
        this.setState({eventAdmins : newEventAdmins});
    },
    handleMarker : function(position) {
        this.setState({latitude : position.lat()});
        this.setState({longitude : position.lng()});
        console.log(this.state.latitude);
    },
    handleLatLng : function() {
        var geocoder = new google.maps.Geocoder();
        var parent = this;
        var latitude;
        var longitude;
        geocoder.geocode({'address': this.refs.address.value}, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                console.log('yes');
                longitude = results[0].geometry.location.lng();
                latitude = results[0].geometry.location.lat();
                console.log(parent.state.longitude);
            }
            if (parent.state.latitude != undefined) {
                longitude = parent.state.longitude;
                latitude = parent.state.latitude;
            }
            parent.onSubmit(latitude, longitude);
        });
    },
    render: function () {
        var errors = <div></div>;
        if (this.state.errors != undefined) {
            console.log(this.state.errors);
            var messages = this.state.errors.map(function(error) {
                return <div>
                    {error.errorMessage}
                </div>
            });

            errors = <div className="alert alert-danger" role="alert">
                {messages}
            </div>
        }
        console.log("Rendering create event");
        var invites = this.state.invites.map(function(connection) {
                return (" " + connection.firstName + " " + connection.lastName);
            });
        var eventAdmins = this.state.eventAdmins.map(function(connection) {
           return (" " + connection.firstName + " " + connection.lastName);
        });
        invites = invites.toString().substring(1);
        eventAdmins = eventAdmins.toString().substring(1);
        return (
            <div>
                <NavBar/>
                <h1 className="text-center">
                    Event Creation
                </h1>
                {errors}
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
                                <div className="form-group">
                                    <div>
                                        <label className="col-sm-2 control-label">Event Tag</label>
                                    </div>
                                    <div className="col-sm-8">
                                        <TagList ref="tagList" />
                                    </div>
                                </div>
                                <div id="eventAdmin">
                                    <ConnectionList actionId="eventAdmins" modalId="eventAdminModal" handleInvite={this.handleEventAdmin} buttonName="Add Event Admins" actionName="Add as Event Admin" type="create"/>
                                </div>
                                <div className="form-group">
                                    <div>
                                        <label className="col-sm-2 control-label" htmlFor="invites"> Event Admins </label>
                                    </div>
                                    <div className="col-sm-8">
                                        <textarea id="eventAdmins" readOnly="readonly" className="form-control" rows="3" value={eventAdmins} />
                                    </div>
                                </div>
                                <div id="invites">
                                    <ConnectionList actionId="inviteList" modalId="inviteModal" handleInvite={this.handleInvite} buttonName="Invite Connections" actionName="Add to Invite List" type="create" />
                                </div>
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
                            <div>
                                <Map data={undefined} marker={this.handleMarker} height='300px' width='350px'/>
                            </div>
                        </div>
                    </div>
                    <input type="button" className="btn btn-default" value="Create Event" onClick={this.handleLatLng}/>
                </form>
            </div>
        );
    }
});

ReactDOM.render(<CreateEvent />, document.getElementById('create_event'));