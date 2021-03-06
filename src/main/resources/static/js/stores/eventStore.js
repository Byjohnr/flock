var EventStore = Reflux.createStore({
    listenables: [EventActions],
    init : function() {
        this.markers = [];
    },
    getInitialState() {
    },
    onSearchEvents : function(type, tagId) {
        console.log(type);
        console.log(tagId);
        var data = {};
        data.type = type;
        data.tagId = tagId;
        $.ajax({
            url : '/api/map/search',
            type: 'POST',
            data: data,
            dataType: 'json',
            success : this.handleMarkers
        });
    },
    handleMarkers : function(data) {
        this.clearMarkers();
        this.pushEvent(data);
    },
    onListEvents: function() {
        $.ajax({
            url: '/api/events',
            dataType: 'json',
            success: this.addEvents
        });
    },
    onListMapEvents: function() {
        $.ajax({
            url: '/api/mapEvents',
            dataType: 'json',
            success: this.addEvents
        })
    },
    addEvents: function (data) {
        this.trigger(data);
    },
    onCreateEvent: function (data, invites, eventAdmins) {
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
                if(errors.length === 1 && errors[0].fieldId === "success") {
                    parent.handleInvites(errors[0].errorMessage, invites, eventAdmins);
                    parent.handleEventAdmins(errors[0].errorMessage, eventAdmins);
                    window.location.replace("/event/" + errors[0].errorMessage);
                } else {
                    parent.pushEvent(errors);
                }
            },
            error : function (data) {
                console.log(data);
            }
        });
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
            success: this.onGetEvent,
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
            success: this.onGetEvent
        });
    },
    onEditInvites: function(invites, eventAdmins) {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        var parent = this;
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: 'text',
            type: 'POST',
            success: function() {
                parent.handleInvites(id, invites, eventAdmins);
            }
        });
    },
    onEditEventAdmins: function(eventAdmins) {
        var parent = this;
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: 'text',
            type: 'POST',
            success: function() {
                parent.handleEventAdmins(id, eventAdmins);
            }
        });
    },
    onGetEvent: function () {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        if (id != 'create') {
            $.ajax({
                url: '/api/event/' + id,
                datatype: 'json',
                success: this.pushEvent
            });
        }
    },
    pushEvent: function (data) {
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
            success: this.onGetEvent,
            error: function(status, thrownError) {
                console.log(thrownError);
                console.log(status);
            }
        })
    },
    onJoinEvent: function() {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/event/join/' + id,
            type: 'POST',
            success: this.onGetEvent
        });
    },
    onListAllEvents: function() {
        $.ajax({
            url: '/api/admin/events',
            dataType: 'json',
            success: this.addEvents
        });
    },
    onDeleteEvent: function(data) {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/admin/events/delete',
            type: 'POST',
            dataType: 'text',
            data: JSON.stringify(data),
            success: this.onListAllEvents
        });
    },
    onGetPublicEventAddresses() {
        $.ajax({
            url: '/api/event/public_event_addresses',
            type: 'GET',
            success: this.addEvents
        });
    },
    onAddMarker : function(marker) {
        this.markers.push(marker);
    },
    clearMarkers : function() {
        this.markers.forEach(function(marker) {
            marker.setMap(null);
        });
        this.markers = [];
    }
});