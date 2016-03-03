var ConnectionStore = Reflux.createStore({
    listenables: [ConnectionActions],
    onGetConnections : function() {
        $.ajax({
            url: "/api/connections/get",
            dataType: 'json',
            success : this.triggerConnections
        })
    },
    triggerConnections : function(data) {
        console.log(data);
        this.trigger(data);
    },
    onGetConnectionGroups : function() {

    },
    onGetConnectionStatus : function() {
        var parent = this;
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        $.ajax({
            url: '/api/connection/status/' + id,
            dataType: 'text',
            success : this.triggerStatus,
            error: function () {
                console.log("ya dun goofed");
            }
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
    handleConnectionAjax : function(url, type) {
        var id = window.location.href.substr(window.location.href.lastIndexOf('/') + 1);
        $.ajax({
            url: url + id,
            dataType: 'text',
            type: type,
            success : this.triggerStatus,
            error: function () {
                console.log("ya dun goofed");
            }
        });
    },
    triggerStatus : function(data) {
        this.trigger(data);
    }

});