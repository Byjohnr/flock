/**
 * Created by chasekoehler on 2/8/16.
 */

var EventElements = React.createClass({
    mixins: [Reflux.connect(EventStore,'event')],
    getInitialState: function() {
        return {event : undefined};
    },
    componentDidMount: function() {
        EventActions.getEvent();
    },
    render: function() {
        console.log("checkpoint 1");
        //var description = this.props.data.description;
        //var location = this.props.data.location;
        //var time = this.props.data.time;
        if(this.state.event === undefined) {
            console.log("checkpoint 2");
            return <div>Loading <i className="fa fa-spin fa-refresh"/> </div>;
        }
        return (
            <div>
                <h3> {this.state.event.name} </h3>
            </div>
        );
    }
});