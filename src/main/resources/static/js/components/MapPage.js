var MapPage = React.createClass({
    mixins: [Reflux.connect(EventStore,'events')],

    getInitialState: function() {
        return {events: undefined};
    },
    componentDidMount: function() {
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