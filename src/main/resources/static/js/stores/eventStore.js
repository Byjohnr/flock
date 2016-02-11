/**
 * Created by jeffrey on 1/29/16.
 */
// TODO jeffreyh 1/29/16
var EventStore = Reflux.createStore({
    listenables: [EventActions],
    init : function() {
        console.log('Init');
    },
    getInitialState() {
        console.log('Initial State');
    },
    onListEvents: function() {
        $.ajax({
            url: '/api/events',
            dataType: 'json',
            success: this.addEvents
        });
    },
    addEvents: function(data) {
        this.events = data;
        this.trigger(this.events);
    },
    onCreateEvent: function(data) {
        console.log(data);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/create',
            dataType:'text',
            type: 'POST',
            data: JSON.stringify(data),
            success: function(data) {
                window.location.replace(data);
                console.log("It worked?!?!?");
            },
            error : function () {
                console.log("error");
            },
            done : function() {
                console.log("done?");
            }
        });

    }
});