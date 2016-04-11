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
        if (this.state.events === undefined) {
            return <div>Loading <i className="fa fa-spin fa-refresh"/></div>;
        }
        return(
            <div>
                <NavBar />
                <Map events={this.state.events} height='800px'/>
            </div>
        );
    }
});
ReactDOM.render(<MapPage />, document.getElementById("map"));