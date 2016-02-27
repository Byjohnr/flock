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
    onCreateEvent: function (data, invites) {
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
                    this.handleInvites(errors[0].errorMessage, invites);
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
        console.log('does it get here?');
    },
    handleInvites: function(eventId, invites) {
        var userIds = [];
        invites.map(function(invite) {
            userIds.push(invite.id);
        });
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/event'+ eventId +'/invites',
            data: JSON.stringify(userIds),
            type: 'POST',
            success: function() {
                window.location.replace("/event/" + url);
            },
            error : function() {
                console.log("Error adding invites");
            }
        });
    },
    onEditEvent: function (data) {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/event/'+ id,
            dataType: 'text',
            type: 'POST',
            data: JSON.stringify(data),
            success: function(data) {
                console.log
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