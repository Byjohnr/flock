var NotificationStore = Reflux.createStore({
    listenables: [NotificationActions],
    init : function() {
    },
    getInitialState() {
    },
    onFetchNotifications: function() {
        $.ajax({
            url: '/api/notifications',
            type: 'GET',
            dataType: 'json',
            success: this.addNotifications
        });
    },
    addNotifications: function(data) {
        this.trigger(data);
    },

    onDeleteNotification: function(data) {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/api/notifications/delete',
            type: 'POST',
            dataType: 'text',
            data:JSON.stringify(data),
            success: this.onFetchNotifications
        })
    }
});