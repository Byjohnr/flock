/**
 * Created by jeffrey on 2/4/16.
 */
var CreateEvent = React.createClass({
    mixins: [Reflux.connect(EventStore,'form')],
    onSubmit: function() {
        var formData = {
            eventName: this.refs.left.eventName.value,
            description: this.refs.left.description.value,
            startDate: this.refs.left.startDate.value + ' ' + this.refs.left.startTime.value,
            endDate: this.refs.left.endDate.value + ' ' + this.refs.left.endTime.value,
            type: this.refs.left.type.value,
            address: this.refs.right.address.value
        };
        EventActions.createEvent(formData);
    },
    render: function() {
        return(
            <div>
                <h1 className="text-center">
                    Event Creation
                </h1>
                <form>
                    <div className="row">
                        <div className="col-sm-7">
                            <CreateEventLeftCol ref="left"/>
                            <input type="button" value="Create Event" onClick={this.onSubmit}/>
                        </div>
                        <div className="col-sm-5">
                            <CreateEventRightCol ref="right"/>
                            </div>
                    </div>
                </form>
            </div>
        );
    }
});

ReactDOM.render(<CreateEvent />, document.getElementById('create_event'));