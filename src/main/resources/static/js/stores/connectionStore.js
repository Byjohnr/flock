var ConnectionStore = Reflux.createStore({
    listenables: [ConnectionActions],
    init : function() {
        this.connectionsAndGroups = {};
    },
    onGetConnections : function(type) {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        var data = {};
        if(type == "create") {
            data = undefined;
        } else if(type == "event") {
            data.eventId = id;
        } else if (type == "chat") {
            data.chatId = id;
        }
        $.ajax({
            url: "/api/connections/get",
            type: 'GET',
            data: data,
            dataType: 'json',
            success : this.triggerConnections
        });
    },
    triggerConnections : function(data) {
        this.trigger(data);
    },
    onGetConnectionGroups : function() {
        $.ajax({
            url: "/api/connectionGroups",
            dataType: 'json',
            success : this.triggerConnections
        });
    },
    onGetConnectionStatus : function() {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        $.ajax({
            url: '/api/connection/status/' + id,
            dataType: 'text',
            success : this.triggerStatus,
            error: function () {
                console.log('Error');
            }
        });
    },
    onGetConnectionGroup : function() {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);

        $.ajax({
            url: '/api/connectionGroup/' + id,
            dataType: 'json',
            success : this.triggerStatus
        })
    },
    onAddConnectionGroup : function(groupName, groupId) {
        var url = '/api/connectionGroup/create';
        if (groupId != undefined) {
            url = '/api/connectionGroup/' + groupId + '/edit'
        }
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: url,
            data: groupName,
            type: 'POST',
            success : function() {
                window.location.reload(true);
            }
        })
    },
    onDeleteConnectionGroup : function(groupId) {
        this.ajaxRequestWithIntValue(groupId, '/api/connectionGroup/delete', 'POST', function() {
            window.location.reload(true);
        });
    },
    onRejectConnection : function() {
        this.handleConnectionAjax('/api/connection/reject/', 'POST');
    },
    onRemoveConnection : function() {
       this.handleConnectionAjax('/api/connection/remove/', 'POST');
    },
    onRequestConnection : function() {
        this.handleConnectionAjax('/api/connection/request/', 'POST');
    },
    onAddConnection : function() {
        this.handleConnectionAjax('/api/connection/add/', 'POST');
    },
    onAcceptConnection : function(userId) {
        this.handleConnectionAjaxForAccept('/api/connection/add/'+userId, 'POST', userId);
    },

    onAddConnectionToGroup : function(userId) {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        this.ajaxRequestWithIntValue(userId, '/api/connectionGroup/' + id + '/add', 'POST', function() {
        });
    },
    onRemoveConnectionFromGroup : function(userId) {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        this.ajaxRequestWithIntValue(userId, '/api/connectionGroup/' + id + '/remove', 'POST', function() {
        });
    },
    onGetConnectionsAndGroups : function(type) {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        var data = {};
        if(type == "create") {
            data = undefined;
        } else if(type == "event") {
            data.eventId = id;
        } else if (type == "chat") {
            data.chatId = id;
        }
        $.ajax({
            url: "/api/connections/get",
            type: 'GET',
            data: data,
            dataType: 'json',
            success : this.setStateConnections
        });
    },
    setStateConnections : function(connections) {
        this.connectionsAndGroups.connections = connections;
        $.ajax({
            url: "/api/connectionGroups",
            dataType: 'json',
            success : this.triggerConnectionsAndGroups
        });
    },
    triggerConnectionsAndGroups : function(groups) {
        this.connectionsAndGroups.groups = groups;
        this.triggerStatus(this.connectionsAndGroups);
    },
    handleConnectionAjax : function(url, type) {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        if (id == 'notifications'){
            id = '';
        }
        $.ajax({
            url: url + id,
            dataType: 'text',
            type: type,
            success : this.triggerStatus,
            error: function () {
                console.log("Error getting notifications");
            }
        });
    },
    handleConnectionAjaxForAccept : function(url, type, userId) {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        if (id == 'notifications'){
            id = '';
        }
        $.ajax({
            url: url,
            dataType: 'text',
            type: type,
            success : function() {
                location.reload();
            },
            error: function () {
                console.log("Error getting notifications");
            }
        });
    },
    ajaxRequestWithIntValue : function(intValue, url, type, successFunction) {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: url,
            data: JSON.stringify(intValue),
            type: type,
            success : successFunction
        });
    },
    triggerStatus : function(data) {
        this.trigger(data);
    }
});