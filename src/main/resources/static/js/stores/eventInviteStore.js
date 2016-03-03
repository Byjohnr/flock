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
        console.log("triggered 2");
        this.trigger (data);
    }

});
