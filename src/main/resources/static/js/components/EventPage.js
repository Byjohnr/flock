/**
 * Created by chasekoehler on 2/8/16.
 */

var EventPage = React.createClass({
    render: function() {
        return (
            <div className="row">
                <div className="col-sm-5 col-sm-offset-3">
                    <h1 className="text-center"> Event </h1>
                    <EventElements/>
                </div>
            </div>
        );
    }
});

ReactDOM.render(<EventPage />, document.getElementById('event_div'));