/**
 * Created by chasekoehler on 2/15/16.
 */

const EditEvent = React.createClass({
    mixins: [Reflux.connect(EventStore, 'edit')],
    onEdit: function () {
        var editData = {
            eventName: this.refs.eventName.value,
            description: this.refs.description.value,
            startDate: this.refs.startDate.value + ' ' + this.refs.startTime.value,
            endDate: this.refs.endDate.value + ' ' + this.refs.endTime.value,
            type: this.refs.type.value,
            address: this.refs.address.value
        };
        EventActions.createEvent(editData);
    },
    timePicker: function(id) {
        $('#' + id).pickatime();
    },
    datePicker: function(id) {
        $('#' + id).pickadate();
    },
    render: function() {
        return (
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Modal title</h4>
                    </div>
                    <div class="modal-body">
                        <p>One fine body&hellip;</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        )
    }
});