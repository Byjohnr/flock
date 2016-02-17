/**
 * Created by chasekoehler on 2/8/16.
 */
var EventPage = React.createClass({
    render: function () {
        return (
            <div className="row">
                <div className="col-sm-5 col-sm-offset-3">
                    <h1 className="text-center"> Event </h1>
                    <EventElements/>
                    <!-- Button trigger modal -->
                    <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#EditModal">
                        Launch demo modal
                    </button>
                    <div class="modal fade" id="EditModal" tabindex="-1" role="dialog" aria-labelledby="EditModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                </div>
                                <div class="modal-body">
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary">Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
});

ReactDOM.render(<EventPage />, document.getElementById('event_div'));