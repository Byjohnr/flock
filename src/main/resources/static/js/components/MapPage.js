var MapPage = React.createClass({
    render: function() {
        return(
            <div>
                <NavBar />
                <Map />
            </div>
        );
    }
});
ReactDOM.render(<MapPage />, document.getElementById("map"));