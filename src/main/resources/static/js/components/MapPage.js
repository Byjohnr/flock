var MapPage = React.createClass({
    mixins: [Reflux.connect(EventStore,'events')],
    getInitialState: function() {
        console.log("initial");
        return {events: undefined};
    },
    componentDidMount: function() {
        console.log("Mounted2");
        EventActions.listEvents();
    },
    render: function() {
        return(
            <div>
                <NavBar />
                <Map events={this.state.events}/>
            </div>
        );
    }
});
ReactDOM.render(<MapPage />, document.getElementById("map"));