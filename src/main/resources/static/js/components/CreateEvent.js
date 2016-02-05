/**
 * Created by jeffrey on 2/4/16.
 */
var CreateEvent = React.createClass({
    render: function() {
        return(
            <div>
                <h1 className="text-center">
                    Event Creation
                </h1>
                <form onSubmit={EventActions.createEvent}>
                    <div className="row">
                        <div className="col-sm-7">
                            <CreateEventLeftCol/>
                        </div>
                        <div className="col-sm-5">
                            <CreateEventRightCol/>
                            </div>
                    </div>
                </form>
            </div>
        );
    }
});

ReactDOM.render(<CreateEvent />, document.getElementById('create_event'));