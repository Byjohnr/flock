/**
 * Created by chasekoehler on 2/8/16.
 */

var EventElements = React.createClass({
    mixins: [Reflux.connect(EventStore,'event')],
    render: function() {
        if(this.state.event === undefined) {
            return <div>Loading <i className="fa fa-spin fa-refresh"/> </div>;
        }
        console.log(this.state.event);
        return (
            <div>
                <h3> {this.state.event.eventName} </h3>
                <h3> {this.state.event.eventDescription} </h3>
                <h3> Start: {this.state.event.eventStart} </h3>
                <h3> End: {this.state.event.eventEnd} </h3>
                <h3> Location: {this.state.event.eventLocation} </h3>
            </div>
        );
    }
});