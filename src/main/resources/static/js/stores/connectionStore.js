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

    }
});