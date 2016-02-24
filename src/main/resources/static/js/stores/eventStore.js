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
    addEvents: function (data) {
        this.trigger(data);
    },
    onCreateEvent: function (data) {
        console.log(data);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/create',
            dataType: 'text',
            type: 'POST',
            data: JSON.stringify(data),
            success: function(data) {
                var errors = JSON.parse(data);
                console.log(errors);
                if(errors.length === 1 && errors[0].fieldId === "success") {
                    window.location.replace("/event/" + errors[0].errorMessage);
                }
                console.log("It worked?!?!?");
            },
            error : function (data) {
                console.log(data);
            },
            done: function () {
                console.log("done?");
            }
        });
    },
    onGetEvent: function () {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        $.ajax({
            url: '/api/event/' + id,
            datatype: 'json',
            success: this.pushEvent
        })
    },
    pushEvent: function (data) {
        this.trigger(data);
    }
});