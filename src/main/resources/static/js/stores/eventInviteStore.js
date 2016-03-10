/**
 * Created by chasekoehler on 3/2/16.
 */
var EventInviteStore = Reflux.createStore({
    listenables: [EventInviteActions],
    init : function() {
        console.log('Init');
    },
    getInitialState() {
        console.log('Initial State');
    },
    onGetAttending() {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        $.ajax({
            url: '/api/event/getAttending/' + id,
            type: 'GET',
            dataType: 'text',
            success: this.pushAttending
        })
    },
    pushAttending (data) {
        this.trigger (data);
    },
    onSetAttending(data) {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/event/setAttending/' + id,
            type: 'POST',
            dataType: 'text',
            data: data,
            success: this.pushAttending,
            error: function(status, thrownError) {
                console.log(thrownError)
                console.log(status);
            }
        })
    }

});
