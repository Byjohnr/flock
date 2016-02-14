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
        //console.log(data);
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
                var errors = JSON.parse(data);
                console.log(errors);
                if(errors.length === 0) {
                    window.location.replace("/");
                }
                console.log("It worked?!?!?");
            },
            error : function (data) {
                console.log(data);
            },
            done : function() {
                console.log("done?");
            }
        });

    }
});