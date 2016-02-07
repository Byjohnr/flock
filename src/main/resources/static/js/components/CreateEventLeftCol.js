/**
 * Created by jeffrey on 2/4/16.
 */
var CreateEventLeftCol = React.createClass({

    componentDidMount : function() {
        //$('#startTime').pickatime();
    },
    timePicker: function(id) {
        $('#' + id).pickatime();
    },
    datePicker: function(id) {
        $('#' + id).pickadate();

    },
    render: function() {
        //TODO jeffreyh 2/4/16 look into pulling the different types from the server
        //TODO jeffreyh 2/6/16, create another component for inviting connections
        return (
            <div className="form-horizontal">
                LEFT SIDE
                <div className="form-group">
                    <div>
                        <label className="col-sm-2 control-label" htmlFor="name">Name of Event</label>
                    </div>
                    <div className="col-sm-8">
                        <input id="name" className="form-control" type="text" ref={(ref) => this.eventName = ref}/>
                    </div>
                </div>
                <div className="form-group">
                    <div>
                        <label className="col-sm-2 control-label" htmlFor="description">Event Description</label>
                    </div>
                    <div className="col-sm-8">
                        <textarea id="description" className="form-control" rows="3" ref={(ref) => this.description = ref}/>
                    </div>
                </div>
                <div className="form-group">
                    <div>
                        <label className="col-sm-2 control-label" htmlFor="startDate">Event Start Time</label>
                    </div>
                    <div className="col-sm-8">
                        <div className="form-inline">
                        <input id="startDate" className="form-control" onClick={this.datePicker.bind(this,'startDate')} ref={(ref) => this.startDate = ref}/>
                        <input id="startTime" className="form-control" onClick={this.timePicker.bind(this,'startTime')} ref={(ref) => this.startTime = ref}/>
                            </div>
                    </div>
                </div>
                <div className="form-group">
                    <div>
                        <label className="col-sm-2 control-label" htmlFor="startDate">Event End Time</label>
                    </div>
                    <div className="col-sm-8">
                        <div className="form-inline">
                            <input id="endDate" className="form-control" onClick={this.datePicker.bind(this,'endDate')} ref={(ref) => this.endDate = ref}/>
                            <input id="endTime" className="form-control" onClick={this.timePicker.bind(this,'endTime')} ref={(ref) => this.endTime = ref}/>
                        </div>
                    </div>
                </div>
                <div className="form-group">
                    <div>
                        <label className="col-sm-2 control-label" htmlFor="type">Event Type</label>

                    </div>
                    <div className="col-sm-8">
                        <select id="type" className="form-control" ref={(ref) => this.type = ref}>
                            <option value="1">Open</option>
                            <option value="2">Connections Only</option>
                            <option value="3">Invite Only</option>
                        </select>
                    </div>
                </div>
            </div>
        );
    }
});