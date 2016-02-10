/**
 * Created by chasekoehler on 2/8/16.
 */

var EventElements = React.createClass({
    mixins: [Reflux.connect(EventStore,'events')],
    getInitialState: function() {
        return {event : undefined};
    },
    componentDidMount: function() {
        EventStore.onEvent();
    },
    render: function() {
        var name = this.props.data.name
        var description = this.props.data.description
        var location = this.props.data.location
        var time = this.props.data.time
        var string = this.props.data.toString()
        return (
            <p>
                {name}
            </p>


        );
    }
});