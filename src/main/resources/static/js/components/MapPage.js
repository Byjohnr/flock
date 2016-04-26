var MapPage = React.createClass({
    mixins: [Reflux.connect(EventStore, 'events')],

    getInitialState: function () {
        return {events: undefined};
    },
    componentDidMount: function() {
        EventActions.listMapEvents();
    },
    handleClick: function () {
        var type = $('#type').val();
        var tagId = this.refs.tagList.tagId.value;
        EventActions.searchEvents(type, tagId);
    },
    render: function () {
        if (this.state.events === undefined) {
            return <div>Loading <i className="fa fa-spin fa-refresh"/></div>;
        }
        return (<div>
                <NavBar />
                <div className="container">
                    <div className="row">
                        <div className="form-inline">
                            <div className="form-group">
                                <label className="control-label" htmlFor="type">Type</label>
                                <select id="type" className="form-control">
                                    <option value="">Select a Type</option>
                                    <option value="1">Open</option>
                                    <option value="2">Connections Only</option>
                                    <option value="4">Invited</option>
                                </select>
                            </div>
                            <div className="form-group">
                                <label className="control-label">Tags</label>
                                <TagList ref="tagList"/>
                            </div>
                            <button onClick={this.handleClick} className="btn btn-primary">Apply Filters</button>
                        </div>
                    </div>
                    <div>
                        <div className="row">
                            <Map data={this.state.events} height='500px'/>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
});
ReactDOM.render(<MapPage />, document.getElementById("map"));