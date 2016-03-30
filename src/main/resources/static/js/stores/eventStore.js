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
    onCreateEvent: function (data, invites, eventAdmins) {
        console.log(data);
        var parent = this;
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
                    parent.handleInvites(errors[0].errorMessage, invites, eventAdmins);
                    parent.handleEventAdmins(errors[0].errorMessage, eventAdmins);
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
    handleInvites: function(eventId, invites, eventAdmins) {
        var userIds = [];
        invites.map(function(invite) {
            userIds.push(invite.id);
        });
        eventAdmins.map(function(admin) {
            userIds.push(admin.id);
        });
        this.handleAjaxInvites('/api/event/'+ eventId +'/invites', JSON.stringify(userIds), eventId);
    },
    handleEventAdmins : function(eventId, eventAdmins) {
        var userIds = [];
        eventAdmins.map(function(admin) {
            userIds.push(admin.id);
        });
        this.handleAjaxInvites('/api/event/'+ eventId +'/admins', JSON.stringify(userIds), eventId);
    },
    handleAjaxInvites : function(url, data, eventId) {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: url,
            data: data,
            type: 'POST',
            success: function () {
                window.location.replace("/event/" + eventId);
            },
            error: function (xhr, status, error) {
                console.log("Error adding invites");
                console.log(status);
                console.log(xhr.responseText)
            }
        })
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
        console.log("triggered");
        this.trigger(data);
    },
    onCreateComment: function (data) {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/event/createComment/' + id,
            type: 'POST',
            dataType: 'text',
            data: data,
            success: function() {
                console.log("reload");
            },
            error: function(status, thrownError) {
                console.log(thrownError);
                console.log(status);
            }
        })
    }

});