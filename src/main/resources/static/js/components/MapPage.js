var MapPage = React.createClass({
    mixins: [Reflux.connect(EventStore,'events')],
    getInitialState: function() {
        return {events: undefined};
    },
    componentDidMount: function() {
        EventActions.listMapEvents();
    },
    render: function() {
        if (this.state.events === undefined) {
            return <div>Loading <i className="fa fa-spin fa-refresh"/></div>;
        }
        return(
            <div>
                <NavBar />
                <Map data={this.state.events} height='700px'/>
            </div>
        );
    }
});
ReactDOM.render(<MapPage />, document.getElementById("map"));