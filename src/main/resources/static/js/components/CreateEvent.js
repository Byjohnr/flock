/**
 * Created by jeffrey on 2/4/16.
 */
var CreateEvent = React.createClass({
    mixins: [Reflux.connect(EventStore,'form')],
    onSubmit: function() {
        var formData = {
            eventName: this.refs.eventName.value,
            description: this.refs.description.value,
            startDate: this.refs.startDate.value + ' ' + this.refs.startTime.value,
            endDate: this.refs.endDate.value + ' ' + this.refs.endTime.value,
            type: this.refs.type.value,
            address: this.refs.address.value
        };
        EventActions.createEvent(formData);
    },
    timePicker: function(id) {
        $('#' + id).pickatime();
    },
    datePicker: function(id) {
        $('#' + id).pickadate();
    },
    render: function() {
        return(
            <div>
                <NavBar/>
                <h1 className="text-center">
                    Event Creation
                </h1>
                <form>
                    <div className="row">
                        <div className="col-sm-7">
                            <div className="form-horizontal">
                                LEFT SIDE
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
                                        <label className="col-sm-2 control-label" htmlFor="description">Event Description</label>
                                    </div>
                                    <div className="col-sm-8">
                                        <textarea id="description" className="form-control" rows="3" ref="description"/>
                                    </div>
                                </div>
                                <div className="form-group">
                                    <div>
                                        <label className="col-sm-2 control-label" htmlFor="startDate">Event Start Time</label>
                                    </div>
                                    <div className="col-sm-8">
                                        <div className="form-inline">
                                            <input id="startDate" className="form-control" onClick={this.datePicker.bind(this,'startDate')} ref="startDate"/>
                                            <input id="startTime" className="form-control" onClick={this.timePicker.bind(this,'startTime')} ref="startTime"/>
                                        </div>
                                    </div>
                                </div>
                                <div className="form-group">
                                    <div>
                                        <label className="col-sm-2 control-label" htmlFor="startDate">Event End Time</label>
                                    </div>
                                    <div className="col-sm-8">
                                        <div className="form-inline">
                                            <input id="endDate" className="form-control" onClick={this.datePicker.bind(this,'endDate')} ref="endDate"/>
                                            <input id="endTime" className="form-control" onClick={this.timePicker.bind(this,'endTime')} ref="endTime"/>
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
                            </div>
                            <input type="button" value="Create Event" onClick={this.onSubmit}/>
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
                </form>
            </div>
        );
    }
});

ReactDOM.render(<CreateEvent />, document.getElementById('create_event'));